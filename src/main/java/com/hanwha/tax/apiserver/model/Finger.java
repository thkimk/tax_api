package com.hanwha.tax.apiserver.model;

import com.hanwha.tax.apiserver.entitysecond.MailHistory;
import com.hanwha.tax.apiserver.entitysecond.MailReceiver;
import com.hanwha.tax.apiserver.repositorysecond.MailHistoryRepository;
import com.hanwha.tax.apiserver.repositorysecond.MailReceiverRepository;
import com.hanwha.tax.apiserver.vo.AskVo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class Finger {

    private final MailReceiverRepository mailReceiverRepository;
    private final MailHistoryRepository mailHistoryRepository;


    public void sendEmail(AskVo askVo) {
        // CS담당자 메일 발송 (핑거 연동)
//        List<MailReceiver> mailReceivers = mailReceiverRepository.findAll();
//        log.info("## ask : {}", mailReceivers);
        MailReceiver mailReceiver = new MailReceiver(askVo);
        mailReceiverRepository.save(mailReceiver);

        MailHistory mailHistory = new MailHistory(askVo);
        mailHistoryRepository.save(mailHistory);

    }

    public void sendEmailAll() {

    }
}
