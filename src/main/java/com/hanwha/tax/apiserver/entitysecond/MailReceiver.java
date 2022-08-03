package com.hanwha.tax.apiserver.entitysecond;


import com.hanwha.tax.apiserver.vo.AskVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "mail_receiver")
@Data
@NoArgsConstructor
public class MailReceiver {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="mail_receiver_no")
    private Long mailReceiverNo;

    @Column(name="mail_receiver_rev_no")
    private Long mailReceiverRevNo;

    @Column(name="mail_no")
    private Long mailNo;

    @Column(name="mail_tmpt_seq")
    private Long mailTmptSeq;

    @Column(name="mail_serialnum")
    private Long mailSerialnum;

    @Column(name="mail_receiver_report_seq")
    private Long mailReceiverReportSeq;

    @Column(name="mail_receiver_name")
    private String mailReceiverName;

    @Column(name="mail_receiver_email")
    private String mailReceiverEmail;

    @Column(name="mail_receiver_replace_json")
    private String mailReceiverReplaceJson;

    @Column(name="mail_receiver_open")
    private Long mailReceiverOpen;

    @Column(name="mail_receiver_open_date")
    private Long mailReceiverOpenDate;

    @Column(name="mail_receiver_link_open")
    private Long mailReceiverLinkOpen;

    @Column(name="mail_receiver_link_open_date")
    private Long mailReceiverLinkOpenDate;

    @Column(name="mail_receiver_smtp_msg")
    private String mailReceiverSmtpMsg;

    @Column(name="mail_receiver_result_code")
    private Long mailReceiverResultCode;

    @Column(name="mail_receiver_resend")
    private Long mailReceiverResend;

    @Column(name="mail_receiver_report_resp")
    private Long mailReceiverReportResp;

    @Column(name="mail_receiver_send_date")
    private Long mailReceiverSendDate;

    @Column(name="mail_receiver_complete_date")
    private Long mailReceiverCompleteDate;

    @Column(name="mail_sender_ip")
    private String mailSenderIp;


    public MailReceiver(AskVo askVo, Long mailNo) {
        Long unixTime = System.currentTimeMillis();

        this.mailNo = mailNo;
        mailReceiverNo = unixTime;
        mailReceiverName = "CS1";
        mailReceiverEmail = "thtmn@naver.com";
        mailReceiverSendDate = unixTime / 1000 + 10;
        {
            mailReceiverRevNo = 0L;
            mailTmptSeq = 0L;
            mailSerialnum = 0L;
            mailReceiverReportSeq = 0L;

            mailReceiverReplaceJson = "";
            mailReceiverOpen = 0L;
            mailReceiverOpenDate = 0L;
            mailReceiverLinkOpen = 0L;
            mailReceiverLinkOpenDate = 0L;

            mailReceiverCompleteDate = 0L;

            mailReceiverSmtpMsg = "";
            mailReceiverResultCode = 1L;
            mailReceiverResend = 1L;
            mailSenderIp = "127.0.0.1";
            mailReceiverReportResp = 0L;
        }
    }


}
