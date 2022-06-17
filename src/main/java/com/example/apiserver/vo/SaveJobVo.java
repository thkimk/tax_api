package com.example.apiserver.vo;

import lombok.Data;

@Data
public class SaveJobVo {
    String custId;
    char businDiv;  // 사업자 구분 : 개인사업자(1),
    String jobCode; // 업종코드
}
