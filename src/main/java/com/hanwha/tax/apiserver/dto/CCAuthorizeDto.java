package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CCAuthorizeDto {
    @JsonProperty("rsp_code")
    private String rspCode;

    @JsonProperty("rsp_message")
    private String rspMessage;

    @JsonProperty("code")
    private String code;

    @JsonProperty("access_code")
    private String accessCode;

    @JsonProperty("expire_in")
    private String expireIn;

}
