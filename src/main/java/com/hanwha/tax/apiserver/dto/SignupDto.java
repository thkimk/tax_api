package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import lombok.Data;

@Data
public class SignupDto {

    @Data
    public static class Additional {
        public Character isMarriage;
        public Character isNewBusin;
        public Character isHshld;
        public Character isDisorder;
        public Character isSinParent;
        public String jobCode;

        public Additional(CustInfoDtl custInfoDtl) {
            isMarriage = custInfoDtl.getIsMarriage();
            isNewBusin = custInfoDtl.getIsNewBusin();
            isHshld = custInfoDtl.getIsHshld();
            isDisorder = custInfoDtl.getIsDisorder();
            isSinParent = custInfoDtl.getIsSinParent();
            jobCode = custInfoDtl.getJobCode();
        }
    }

    @JsonProperty("jwt")
    String jwt;
    String custGrade;
    String custStatus;

    @JsonProperty("user")
    MemberDto member;
    Additional additional;

}
