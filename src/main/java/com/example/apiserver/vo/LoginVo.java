package com.example.apiserver.vo;

import lombok.Data;

@Data
public class LoginVo {
    String cust_id;
    String pin;
    String push_token;

}
