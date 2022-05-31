package com.example.apiserver.vo;

import lombok.Data;

@Data
public class SignupVo {
    String pin;
    String ci;
    String name;
    String email;
    String birth;
    String mobile;
    char gender;
    char is_marriage;
    char is_agree_terms;
    String push_token;
    String dev_uid;

}
