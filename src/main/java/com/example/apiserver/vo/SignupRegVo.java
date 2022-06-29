package com.example.apiserver.vo;

import lombok.Data;

@Data
public class SignupRegVo {
    String custId;
    String taxToken;
    String ci;
    String name;
    String birth;
    String mobile;
    String resultCode;
    String resultMsg;

}
