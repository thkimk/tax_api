package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.vo.UserVo;
import lombok.Data;

@Data
public class MemberDto {
    @JsonProperty("jwt")
    private String jwt;

    @JsonProperty("user")
    private UserVo user;

}
