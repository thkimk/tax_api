package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.Cust;
import lombok.Data;

@Data
public class LoginDto {
    String jwt;
    String cid;
    String custGrade;
    String custStatus;

    String taxFlag = "11";

//    char isSucc;   // 로그인 성공여부 : 성공(1), 성공아님(0)
//    Character isMydata;    // 자산연동 여부 : 연동(Y), 연동아님(N)

    public void fillCust(Cust cust) {
        cid = cust.getCid();
        custGrade = cust.getCustGrade();
        custStatus = cust.getCustStatus();
    }


    /**
     * 소득세 산출 flag 추출
     * @param preIncome : 직전년도 수입 (입력과 마이데이터 존재시, 입력을 우선적으로)
     * @param income : 당해년도 수입
     */
    public void fillTaxFlag(Long preIncome, Long income) {
        if (preIncome == null) {
            taxFlag = "00";             // No data!!
            return;
        }

        // 2400만원 미만
        if (preIncome < 24000000) {
            taxFlag = "11";             // 간편, 단순경비
            if (income != null && income >= 75000000) {
                taxFlag = "12";         // 간편, 기준경비
            }
        }
        // 7500만원 미만
        else if (preIncome < 75000000) {
            taxFlag = "12";             // 간편, 기준경비
        }
        // 7500만원 이상
        else {
            taxFlag = "22";             // 복식, 기준경비
        }
    }
}
