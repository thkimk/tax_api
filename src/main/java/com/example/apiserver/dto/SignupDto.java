package com.example.apiserver.dto;

import lombok.Data;

@Data
public class SignupDto {
    String custId;
    char isSucc;
    String failMsg;
}
