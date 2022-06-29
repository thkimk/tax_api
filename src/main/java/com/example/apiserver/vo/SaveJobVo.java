package com.example.apiserver.vo;

import lombok.Data;

@Data
public class SaveJobVo {
    String custId;
    char isNewBusin;    // 신규사업자면 Y, 아니면 N
    String jobCode;     // 업종코드
}
