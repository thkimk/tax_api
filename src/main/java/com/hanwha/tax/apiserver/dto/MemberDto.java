package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.model.User;
import com.hanwha.tax.apiserver.vo.SignupVo;
import com.hanwha.tax.apiserver.vo.UserVo;
import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class MemberDto {
    @JsonProperty("jwt")
    private String jwt;

    @JsonProperty("user")
    private UserVo user;

}
