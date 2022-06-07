package com.example.apiserver.dto;

import lombok.Data;

@Data
public class LoginDto {
    String jwtToken;
    String custId;
    String custGrade;
    char isSucc;   // 로그인 성공여부 : 성공(1), 성공아님(0)
    char isFin;    // 자산연동 여부 : 연동(1), 연동아님(0)
}
