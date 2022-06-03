package com.example.apiserver.dto;

import lombok.Data;

@Data
public class LoginDto {
    String jwt_token;
    String cust_id;
    String cust_grade;
    char is_succ;   // 로그인 성공여부 : 성공(1), 성공아님(0)
    char is_fin;    // 자산연동 여부 : 연동(1), 연동아님(0)
}
