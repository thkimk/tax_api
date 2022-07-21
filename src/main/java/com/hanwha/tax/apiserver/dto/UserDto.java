package com.hanwha.tax.apiserver.dto;


import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.model.User;
import com.hanwha.tax.apiserver.model.YesOrNo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

@Data
@NoArgsConstructor
public class UserDto {
    @NonNull
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
    private User.Telecom telecom;
    @NonNull
    private String email;
    @NonNull
    private String grade;
    @NonNull
    private String status;

    @Nullable
    private Additional additional;

    public UserDto(SignupVo signupVo) {
        cid = signupVo.getCid();
        name = signupVo.getName();
        birth = signupVo.getBirth();
        gender = signupVo.getGender();
        mobile = signupVo.getMobile();
        telecom = signupVo.getTelecom();
        email = signupVo.getEmail();
    }

    public UserDto(UserInterface user) {
        cid = user.getCid();
        name = user.getName();
        birth = user.getBirth();
        gender = User.Gender.parse(user.getGender());
        mobile = user.getMobile();
        telecom = User.Telecom.parse(user.getTelecom());
        email = user.getEmail();
        grade = user.getGrade();
        status = user.getStatus();
    }

    public void setAdditional(@Nullable CustInfoDtl custInfoDtl) {
        if (custInfoDtl == null) {
            return;
        }
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

        public Additional(@Nullable CustInfoDtl custInfoDtl) {
            if (custInfoDtl == null) {
                return;
            }
            isMarriage = YesOrNo.parse(custInfoDtl.getIsMarriage());
            isNewBusin = YesOrNo.parse(custInfoDtl.getIsNewBusin());
            isHshld = YesOrNo.parse(custInfoDtl.getIsHshld());
            isDisorder = YesOrNo.parse(custInfoDtl.getIsDisorder());
            isSinParent = YesOrNo.parse(custInfoDtl.getIsSinParent());
            jobCode = custInfoDtl.getJobCode();
        }
    }
}
