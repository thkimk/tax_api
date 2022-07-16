package com.hanwha.tax.apiserver.model;


import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.Utils;
import lombok.Data;

@Data
public class Tax {
    // 기준정보
    String cid;
    Character businType;
    String taxFlag;

    // 01.소득(earning) = 수입 - 지출
    Long earning = 0L;
    Long income = 0L;
    Long outgoing = 0L;

    // 02.과세표준(taxBase) = 01.소득 - 소득공제
    Long taxBase = 0L;
    Long deduct = 0L;

    // 03.산출세액(calTax) = 02.과표 * 세율(6%~45%)
    Long calTax = 0L;
    int taxRate = 0;

    // 04.결정세액(decTax)
    Long decTax = 0L;
    Long taxDeduct = 0L;

    // 05.최종세액(finTax)
    Long finTax = 0L;
    Long addTax = 0L;
    Long paidTax = 0L;

    public Tax(String cid) {
        this.cid = cid;
    }

    /**
     * 예상 소득세 계산기
     * @param taxFlag : 간편장부(10), 기준경비율(02)
     * @return : 예상 소득세
     */
    public Long calTax(int taxFlag) {
        // 01.소득 계산
        earning = income - outgoing;

        // 02.과세표준 계산
        taxBase = earning - deduct;

        // 03.산출세액 계산
        taxRate = taxRate(taxBase);
        calTax = taxBase * taxRate;

        // 04.결정세액 계산
        decTax = calTax - taxDeduct;

        // 05.최종세액 계산
        finTax = decTax + addTax - paidTax;

        return finTax;
    }


    /**
     * 과세표준 세율
     * @param taxBase : 과세표준
     * @return : %
     */
    public static int taxRate(Long taxBase) {
        if (taxBase <= 12000000) return 6;
        else if (taxBase <=  46000000) return 15;
        else if (taxBase <=  88000000) return 24;
        else if (taxBase <= 150000000) return 35;
        else if (taxBase <= 120000000) return 38;
        else if (taxBase <= 300000000) return 40;
        else if (taxBase <= 500000000) return 42;
        else return 45;
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
