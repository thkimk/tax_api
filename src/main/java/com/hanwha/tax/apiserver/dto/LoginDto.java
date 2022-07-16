package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.Cust;
import com.hanwha.tax.apiserver.model.Tax;
import lombok.Data;

import static com.hanwha.tax.apiserver.model.Tax.taxFlag;

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
     * @param businType : 신규 여부 (0, 1)
     */
    public void fillTaxFlag(Long preIncome, Long income, Character businType) {
        taxFlag = Tax.taxFlag(preIncome, income, businType);
    }
}
