package com.hanwha.tax.apiserver.vo;


import com.hanwha.tax.apiserver.model.User;
import lombok.Data;

@Data
public class IdenOtpReqVo {
    String name;
    String birth;
    User.Gender sexCd;   // M, F
    char frnrCd;   // L, F
    String telComCd;
    String telNo;
    String txSeqNo;
}
