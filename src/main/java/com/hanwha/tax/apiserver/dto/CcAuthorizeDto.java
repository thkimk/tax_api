package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CcAuthorizeDto {
    @JsonIgnore
    private String rspCode;

    @JsonIgnore
    private String rspMessage;

    @JsonProperty("code")
    private String code;

    @JsonProperty("access_code")
    private String accessCode;

    @JsonIgnore
    private String expireIn;

}
