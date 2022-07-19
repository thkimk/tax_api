package com.hanwha.tax.apiserver.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.model.type.User;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

@Data
public class SignupVo {
    @JsonIgnore
    String cid;

    String name;
    String birth;
    User.Gender gender;
    String mobile;
    String email;

    YesOrNo isMarriage;
    YesOrNo isNewBusin;
    YesOrNo isHshld;
    YesOrNo isDisorder;
    YesOrNo isSinParent;
    String jobCode;
    String income;

    String ci;
    String pin;
    String pushToken;
    String agreeTerms;

}
