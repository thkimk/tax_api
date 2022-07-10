package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class SignupRegVo {
    String cid;
    String taxToken;
    String ci;
    String name;
    String birth;
//    String mobile;
    String resultCode;
    String resultMsg;

}
