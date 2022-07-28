package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class SignoutRegVo {
    String cid;
    String taxToken;
    String ci;
    String resultCode;
    String resultMsg;

}
