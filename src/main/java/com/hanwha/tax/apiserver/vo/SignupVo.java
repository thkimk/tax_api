package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class SignupVo {
    String name;
    String birth;
    char gender;
    String mobile;
    String email;

    char isMarriage;
    char isNewBusin;
    char isHshld;
    char isDisorder;
    char isSinParent;
    String jobCode;
    String income;

    String ci;
    String pin;
    String pushToken;
    String agreeTerms;

}
