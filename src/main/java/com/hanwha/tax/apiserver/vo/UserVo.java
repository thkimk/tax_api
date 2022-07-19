package com.hanwha.tax.apiserver.vo;

import com.hanwha.tax.apiserver.entity.CustInfo;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.model.User;
import com.hanwha.tax.apiserver.model.YesOrNo;
import lombok.Data;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
public class UserVo {
    @Nullable
    private String cid;
    @NonNull
    private String name;
    @NonNull
    private String birth;
    @NonNull
    private User.Gender gender;
    @NonNull
    private String mobile;
    @NonNull
    private String email;

    @NonNull
    private String grade;
    @NonNull
    private String status;

    @Nullable
    private Additional additional;

    public UserVo(SignupVo signupVo) {
        cid = signupVo.getCid();
        name = signupVo.getName();
        birth = signupVo.getBirth();
        gender = signupVo.getGender();
        mobile = signupVo.getMobile();
        email = signupVo.getEmail();
    }

    public UserVo(CustInfo custInfo) {
        cid = custInfo.getCid();
        name = custInfo.getName();
        birth = custInfo.getBirth();
        gender = User.Gender.parse(custInfo.getGender());
        mobile = custInfo.getMobile();
        email = custInfo.getEmail();
    }

    public void setAdditional(CustInfoDtl custInfoDtl) {
        this.additional = new Additional(custInfoDtl);
    }

    @Data
    public static class Additional {
        @Nullable
        public YesOrNo isMarriage;
        @Nullable
        public YesOrNo isNewBusin;
        @Nullable
        public YesOrNo isHshld;
        @Nullable
        public YesOrNo isDisorder;
        @Nullable
        public YesOrNo isSinParent;
        @Nullable
        public String jobCode;

        public Additional(CustInfoDtl custInfoDtl) {
            isMarriage = YesOrNo.parse(custInfoDtl.getIsMarriage());
            isNewBusin = YesOrNo.parse(custInfoDtl.getIsNewBusin());
            isHshld = YesOrNo.parse(custInfoDtl.getIsHshld());
            isDisorder = YesOrNo.parse(custInfoDtl.getIsDisorder());
            isSinParent = YesOrNo.parse(custInfoDtl.getIsSinParent());
            jobCode = custInfoDtl.getJobCode();
        }
    }
}
