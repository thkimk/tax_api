package com.hanwha.tax.apiserver.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.entity.*;
import com.hanwha.tax.apiserver.repository.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@Slf4j
public class Tax {
    // 기준정보
    String cid;
    int year;
    Character businType;
    String taxFlag = Constants.TAX_FLAG_SBSTR;

    // 01.소득(earning) = 수입 - 지출
    Long earning = 0L;
    Long income = 0L;
    Long outgoing = 0L;

    // 02.과세표준(taxBase) = 01.소득 - 소득공제
    Long taxBase = 0L;
    Long deduct = 0L;

    // 03.산출세액(calTax) = 02.과표 * 세율(6%~45%)
    Long calTax = 0L;
    Float taxRate = 0f;

    // 04.결정세액(decTax)
    Long decTax = 0L;
    Long taxDeduct = 0L;

    // 05.최종세액(finTax)
    Long finTax = 0L;
    Long addTax = 0L;
    Long paidTax = 0L;

    CustInfoDtl custInfoDtl = null;
    CustInfo custInfo = null;
    List<CustFamily> custFamilyList = null;
    CustDeduct custDeduct = null;
    Long[] incomes = null;

    private final TotalIncomeRepository totalIncomeRepository;
    private final CustDeductRepository custDeductRepository;
    private final CustInfoRepository custInfoRepository;
    private final CustFamilyRepository custFamilyRepository;
    private final CustInfoDtlRepository custInfoDtlRepository;
    private final IndustryRepository industryRepository;


    public void saveTaxFlag(String cid, int year) {
        this.year = year;

        incomes = custDeductRepository.selectIncomes("2206000001", this.year);
        custInfoDtl = custInfoDtlRepository.findByCid("2206000001");

        taxFlag = taxFlag(incomes[0], incomes[1], custInfoDtl.getIsNewBusin());
    }


    public Long calRateTax(String cid) {
        this.cid = cid;
        log.info("## 소득세 계산(calRateTax) : {}, taxFlag {}", cid, taxFlag);

        income = totalIncomeRepository.selectRtIncome(cid, year);

        Industry industry = industryRepository.findOneByCode(custInfoDtl.getJobCode());
        if ((Integer.parseInt(taxFlag)%10) == 1) {    // 단순경비율
            outgoing = Double.valueOf((Math.min(income, 40000000) * industry.getSimpleExrt().doubleValue() + Math.max(income-40000000, 0) * industry.getSimpleExrtExc().doubleValue())/100).longValue();
        } else {    // 기준경비율
            outgoing = Double.valueOf((income * industry.getStandardExrt().doubleValue())/100).longValue();
        }

        earning = income - outgoing;

        log.info("## [1] 소득 : {} = {} - {}", earning, income, outgoing);
        return calTax(earning);
    }

    public Long calBookTax() {
        log.info("## 소득세 계산(calRateTax) : {}, taxFlag {}", cid, taxFlag);

        this.cid = cid;
        income = totalIncomeRepository.selectRtIncome(cid, year);
        outgoing = totalIncomeRepository.selectRtOutgoing(cid, year);
        earning = income - outgoing;

        log.info("## [1] 소득 : {} = {} - {}", earning, income, outgoing);
        return calTax(earning);
    }

    /**
     * 예상 소득세 계산기
     * @param taxFlag : 간편장부(10), 기준경비율(02), 단순경비율(01)
     * @return : 예상 소득세
     */
    public Long calTax(Long taxFlag) {
        // 01.소득 계산
        // income : totalIncome이 아니고, 계산시에는 실시간 수입 (배치처리 필요 : mydata와 book상의 income을 합산)
        // outgoing : flag 선택값에 따라 다름
        earning = income - outgoing;

        // 02.과세표준 계산
        // deduct(소득공제) 계산 : 본인, 부양가족, 기타
        deduct();
        taxBase = earning - deduct;
        log.info("## [2] 과표 : {} = {} - {}", taxBase, earning, deduct);

        // 03.산출세액 계산
        taxRate = taxRate(taxBase);
        calTax = Double.valueOf(taxBase * taxRate).longValue();
        log.info("## [3] 산출 : {} = {} * {}", calTax, taxBase, taxRate);

        // 04.결정세액 계산
        taxDeduct();
        decTax = calTax - taxDeduct;
        log.info("## [4] 결정 : {} = {} - {}", decTax, calTax, taxDeduct);

        // 05.최종세액 계산
        finTax();
        finTax = decTax + addTax - paidTax;
        finTax = (finTax / 10) * 10;
        log.info("## [5] 최종 : {} = {} + {} - {}", finTax, decTax, addTax, paidTax);

        return finTax;
    }

    void finTax() {
        // 가산세 : 계속사업자이고 직전년도 소득이 4800만 이상인 경우
        if (custInfoDtl.getIsNewBusin() == 'Y' && incomes[1] >= 48000000) {
            addTax = Double.valueOf(calTax * 0.2).longValue();
        }

        // 기납부세액 : 수입금액의 3% --> income에는 모두 3% 기납부세액이 포함되어있어야 함 (미포함시, income계산때 강제 추가)
        Long income33 = totalIncomeRepository.selectRtIncome33(cid, year);
        paidTax = Double.valueOf(income33 * 0.03).longValue();

    }

    void taxDeduct() {
        taxDeduct = 0L;

        // 자녀세액 공제 : 만7세 이상 자녀
        // 당해년도 출생신고 : 한국나이 1세 자녀, 300000/ 500000/ 700000
        int count = 0, countBorn = 0;
        for (CustFamily custFamily : custFamilyList) {
            if (custFamily.getFamily().equals("06") && Utils.realAge(custFamily.getBirth()) >= 7) {
                count++;
                if (count < 3) {
                    taxDeduct += 150000;
                }
            }

            if (custFamily.getFamily().equals("06") && Utils.koreaAge(custFamily.getBirth()) == 1) {
                countBorn++;
                if (countBorn == 1) taxDeduct += 300000;
                else if (countBorn == 2) taxDeduct += 500000;
                else taxDeduct += 700000;
            }
        }
        if (count >= 3) {
            taxDeduct += 300000 + (count-3)/2 * 600000;
        }

        // 연금계좌 세액공제 : IRP는? --> Mydata 제공 IRP??
        if (earning <= 40000000) {
            taxDeduct += Double.valueOf((custDeduct.getIraAmount()) * 0.15).longValue();
        } else {
            taxDeduct += Double.valueOf((custDeduct.getIraAmount()) * 0.12).longValue();
        }

        // 표준 세액공제 : 70000
        // 전자 세액공제 : 20000
        taxDeduct += 70000 + 20000;

    }

    /**
     * 공제금액 계산 (소득공제)
     * 본인 및 부양가족
     * 공제항목
     */
    void deduct() {
        custInfo = custInfoRepository.findByCid(cid);
        custFamilyList = custFamilyRepository.findAllByCid(cid);
        custDeduct = custDeductRepository.findByCidAndYear(cid, year);

        // 본인 공제
        Long deductMe = deductMe();

        // 부양가족 공제
        Long deductFamily = deductFamily();

        // 기타 공제
        Long deductOthers = deductOthers();

        deduct = deductMe + deductFamily + deductOthers;
        log.info("-- [2.1] 소득공제 : {} = {} + {} + {}", deduct, deductMe, deductFamily, deductOthers);
    }

    Long deductMe() {
        Long deductMe = 1500000L;

        // 장애인
        if (custInfoDtl.getIsDisorder().equals("Y")) deductMe += 2000000;

        // 경로우대자
        if (Utils.realAge(custInfo.getBirth()) >= 70) deductMe += 1000000;

        // 한부모 공제 : 고객이 직접 체크한 값으로 계산
        boolean isFlag = false;
        if (custInfoDtl.getIsSinParent().equals("Y")) {
            deductMe += 1000000;
            isFlag = true;
        }

        // 부녀자 : 3천만원 소득 이하의 여성으로
        if (!isFlag) {
            if (custInfo.getGender().equals("F") && earning <= 30000000) {
                if (custInfoDtl.getIsMarriage().equals("Y")) {
                    deductMe += 500000;
                } else if (custInfoDtl.getIsHshld().equals("Y") && custFamilyList.size() > 0) {
                    deductMe += 500000;
                }
            }
        }

        return deductMe;
    }

    Long deductFamily() {
        Long deductFamily = 0L;
        for (CustFamily custFamily : custFamilyList) {
            deductFamily += 1500000;

            if (custFamily.getIsDisorder().equals("Y"))  deductFamily += 2000000;
            if (Utils.realAge(custFamily.getBirth()) >= 70)  deductFamily += 1000000;
        }

        return deductFamily;
    }

    Long deductOthers() {
        Double deductOthers = 0d;

        deductOthers += custDeduct.getNpcAmount();
        deductOthers += Math.min(custDeduct.getRspAmount()*0.4, 720000);   // 한도가 720000원
        deductOthers += earning> 100000000? Math.min(custDeduct.getMedAmount(), 2000000):
                earning> 40000000? Math.min(custDeduct.getMedAmount(), 3000000): Math.min(custDeduct.getMedAmount(), 5000000);
        deductOthers += Math.min(earning*0.5, custDeduct.getSedAmount()*0.1);

        return deductOthers.longValue();
    }


    /**
     * 과세표준 세율
     * @param taxBase : 과세표준
     * @return : %
     */
    public static float taxRate(Long taxBase) {
        float res = 0f;
        if (taxBase <= 12000000) res = 6;
        else if (taxBase <=  46000000) res = 15;
        else if (taxBase <=  88000000) res = 24;
        else if (taxBase <= 150000000) res = 35;
        else if (taxBase <= 120000000) res = 38;
        else if (taxBase <= 300000000) res = 40;
        else if (taxBase <= 500000000) res = 42;
        else res = 45;

        return (res / 100);
    }


    /**
     * 소득세 산출 flag 추출
     * @param preIncome : 직전년도 수입 (입력과 마이데이터 존재시, 입력을 우선적으로)
     * @param income : 당해년도 수입
     * @param businType : 신규 여부 (Y, N)
     */
    public static String taxFlag(Long preIncome, Long income, Character businType) {
        // 신규 사업자(Y)
        if (businType == 'Y') {
            if (income == null) {
                return Constants.TAX_FLAG_NONE;            // No data!!
            }

            // 7500만원 미만
            if (income < 75000000) {
                return Constants.TAX_FLAG_SBSIR;            // 간편, 단순경비
            }
            // 7500만원 이상
            else {
                return Constants.TAX_FLAG_SBSTR;            // 간편, 기준경비
            }
        }
        // 계속 사업자(N)
        else {
            if (preIncome == null) {
                return Constants.TAX_FLAG_NONE;            // No data!!
            }

            // 2400만원 미만
            if (preIncome < 24000000) {
                if (income != null && income >= 75000000) {
                    return Constants.TAX_FLAG_SBSTR;        // 간편, 기준경비
                } else {
                    return Constants.TAX_FLAG_SBSIR;        // 간편, 단순경비
                }
            }
            // 7500만원 미만
            else if (preIncome < 75000000) {
                return Constants.TAX_FLAG_SBSTR;            // 간편, 기준경비
            }
            // 7500만원 이상
            else {
                return Constants.TAX_FLAG_CBSTR;            // 복식, 기준경비
            }
        }
    }

}
