package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class SignupRegVo {
    String cid;
    String taxToken;
    String ci;
    String resultCode;
    String resultMsg;

}
