package com.example.apiserver.dto;

import com.example.apiserver.entity.CustInfoDtl;
import com.example.apiserver.repository.CustInfoDtlRepository;
import com.example.apiserver.vo.SignupVo;
import lombok.Data;
import org.slf4j.MDC;

@Data
public class SignupDto {
    @Data
    public static class User {
        public String custId;
        public String name;
        public String birth;
        public Character gender;
        public String mobile;
        public String email;

        public User(SignupVo signupVo) {
            custId = MDC.get("custId");
            name = signupVo.getName();
            birth = signupVo.getBirth();
            gender = signupVo.getGender();
            mobile = signupVo.getMobile();
            email = signupVo.getEmail();
        }
    }

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

    String jwt;
    User user;
    Additional additional;
}
