package com.hanwha.tax.apiserver.vo;


import lombok.Data;

@Data
public class SaveAuthVo {
    String pin;
    String ci;
    String name;
    String email;
    String birth;
    String mobile;
    char gender;
    char isMarriage;
    char isAgreeTerms;
    String pushToken;
    String devUid;
}
