package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.Cust;
import lombok.Data;

@Data
public class LoginDto {
    String jwt;
    String custId;
    String custGrade;
    String custStatus;
//    char isSucc;   // 로그인 성공여부 : 성공(1), 성공아님(0)
//    Character isMydata;    // 자산연동 여부 : 연동(Y), 연동아님(N)

    public void fillCust(Cust cust) {
        custId = cust.getCustId();
        custGrade = cust.getCustGrade();
        custStatus = cust.getCustStatus();
    }
}
