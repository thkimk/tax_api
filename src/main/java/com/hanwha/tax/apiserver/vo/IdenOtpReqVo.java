package com.hanwha.tax.apiserver.vo;


import lombok.Data;

@Data
public class IdenOtpReqVo {
    String name;
    String birth;
    char sexCd;   // M, F
    char frnrCd;   // L, F
    String telComCd;
    String telNo;
    String txSeqNo;
}
