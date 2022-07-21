package com.hanwha.tax.apiserver.model;


import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.entity.Industry;
import com.hanwha.tax.apiserver.repository.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Data
@Component
@Slf4j
public class Tax {
    // 기준정보
    String cid;
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

    private final TotalIncomeRepository totalIncomeRepository;
    private final CustDeductRepository custDeductRepository;
    private final CustInfoDtlRepository custInfoDtlRepository;
    private final IndustryRepository industryRepository;


    public void saveTaxFlag(String cid, int year) {
        Long[] income = custDeductRepository.selectIncomes("2206000001", year);
        custInfoDtl = custInfoDtlRepository.findByCid("2206000001");

        taxFlag = taxFlag(income[0], income[1], custInfoDtl.getIsNewBusin());
    }


    public Long calRateTax(String cid) {
        this.cid = cid;
        log.info("## 소득세 계산(calRateTax) : {}, taxFlag {}", cid, taxFlag);

        income = totalIncomeRepository.selectRtIncome(cid);

        Industry industry = industryRepository.findOneByCode(custInfoDtl.getJobCode());
        if ((Integer.parseInt(taxFlag)%10) == 1) {    // 단순경비율
            outgoing = Math.round((Math.min(income, 40000000) * industry.getSimpleExrt().doubleValue() + Math.max(income-40000000, 0) * industry.getSimpleExrtExc().doubleValue())/100);
        } else {    // 기준경비율
            outgoing = Math.round((income * industry.getStandardExrt().doubleValue())/100);
        }

        earning = income - outgoing;

        log.info("## [1] 소득 : {} = {} - {}", earning, income, outgoing);
        return calTax(earning);
    }

    public Long calBookTax() {
        log.info("## 소득세 계산(calRateTax) : {}, taxFlag {}", cid, taxFlag);

        this.cid = cid;
        income = totalIncomeRepository.selectRtIncome(cid);
        outgoing = totalIncomeRepository.selectRtOutgoing(cid);
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
//        income = ;
//        outgoing = ;
        earning = income - outgoing;

        // 02.과세표준 계산
        // deduct(소득공제) 계산 : 본인, 부양가족, 기타
        deduct();
        taxBase = earning - deduct;
        log.info("## [2] 과표 : {} = {} - {}", taxBase, earning, deduct);

        // 03.산출세액 계산
        taxRate = taxRate(taxBase);
        calTax = Math.round((double)(taxBase * taxRate));
        log.info("## [3] 산출 : {} = {} * {}", calTax, taxBase, taxRate);

        // 04.결정세액 계산
        decTax = calTax - taxDeduct;
        log.info("## [4] 결정 : {} = {} - {}", decTax, calTax, taxDeduct);

        // 05.최종세액 계산
        finTax = decTax + addTax - paidTax;
        log.info("## [5] 최종 : {} = {} + {} = {}", finTax, decTax, addTax, paidTax);

        return finTax;
    }

    /**
     * 공제금액 계산 (소득공제)
     * 본인 및 부양가족
     * 공제항목
     */
    void deduct() {

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
