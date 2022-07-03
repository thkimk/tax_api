package com.hanwha.tax.apiserver.vo;

import lombok.Data;

@Data
public class LoginVo {
    String custId;
    String pin;
    String pushToken;

}
