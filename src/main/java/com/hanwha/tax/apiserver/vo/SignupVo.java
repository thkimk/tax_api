package com.hanwha.tax.apiserver.vo;

import com.hanwha.tax.apiserver.model.User;
import lombok.Data;

@Data
public class SignupVo {
    private String cid;

    private String name;
    private String birth;
    private User.Gender gender;
    private String mobile;
    private User.Telecom telecom;
    private String email;

    private String ci;
    private String pin;
    private String pushToken;
    private String agreeTerms;

}
