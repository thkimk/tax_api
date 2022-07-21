package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.vo.SignupVo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "noti_setting")
public class NotiSetting extends TimeEntity {
    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Column(name="push_yn")
    private Character pushYn;

    @Column(name="sms_yn")
    private Character smsYn;

    @Column(name="email_yn")
    private Character emailYn;

    @Column(name="talk_yn")
    private Character talkYn;


    public NotiSetting(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        String notiAgmts = signupVo.getAgreeTerms().split(";")[1];
        pushYn = notiAgmts.charAt(0);
        smsYn = notiAgmts.charAt(1);
        emailYn = notiAgmts.charAt(2);
        talkYn = notiAgmts.charAt(3);
    }


}
