package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class LoginVo {
    String cid;
    String pin;
    String pushToken;

}
