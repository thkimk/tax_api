package com.example.apiserver.vo;

import lombok.Data;

@Data
public class LoginVo {
    String custId;
    String pin;
    String pushToken;

}
