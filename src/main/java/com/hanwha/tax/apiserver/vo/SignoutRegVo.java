package com.hanwha.tax.apiserver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SignoutRegVo {
    String cid;

    @JsonProperty("tax_token")
    String taxToken;

    String ci;
    String resultCode;
    String resultMsg;

}
