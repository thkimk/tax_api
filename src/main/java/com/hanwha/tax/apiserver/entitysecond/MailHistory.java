package com.hanwha.tax.apiserver.entitysecond;


import com.hanwha.tax.apiserver.vo.AskVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mail_history")
@Data
@NoArgsConstructor
public class MailHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mail_no")
    private Long mailNo;

    @Column(name="mail_rev_no")
    private Long mailRevNo;

    @Column(name="mail_tmpt_seq")
    private Long mailTmptSeq;

    @Column(name="mail_serialnum")
    private Long mailSerialnum;

    @Column(name="mail_send_flag")
    private Long mailSendFlag;

    @Column(name="mail_send_order")
    private Long mailSendOrder;

    @Column(name="mail_send_reserve")
    private Long mailSendReserve;

    @Column(name="mail_send_count")
    private Long mailSendCount;

    @Column(name="mail_send_subject")
    private String mailSendSubject;

    @Column(name="mail_send_contents")
    private String mailSendContents;

    @Column(name="mail_send_resend_yn")
    private Long mailSendResendYn;

    @Column(name="mail_send_userid")
    private Long mailSendUserid;

    @Column(name="mail_send_name")
    private Long mailSendName;

    @Column(name="mail_send_email")
    private String mailSendEmail;

    @Column(name="mail_send_success_count")
    private Long mailSendSuccessCount;

    @Column(name="mail_send_failed_count")
    private Long mailSendFailedCount;

    @Column(name="mail_send_deny_count")
    private Long mailSendDenyCount;

    @Column(name="mail_send_date")
    private Long mailSendDate;

    @Column(name="mail_send_complete_date")
    private Long mailSendCompleteDate;


    public MailHistory(AskVo askVo) {
    }

}
