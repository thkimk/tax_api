package com.example.apiserver.dto;

import lombok.Data;

@Data
public class SignupDto {
    String cust_id;
    char is_succ;
    String fail_msg;
}
