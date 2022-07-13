package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.Data;

@Data
public class SignupDto {
    @Data
    public static class User {
        public String cid;
        public String name;
        public String birth;
        public Character gender;
        public String mobile;
        public String email;

        public User(SignupVo signupVo) {
            cid = signupVo.getCid();

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
    String custGrade;
    String custStatus;

    User user;
    Additional additional;

}
