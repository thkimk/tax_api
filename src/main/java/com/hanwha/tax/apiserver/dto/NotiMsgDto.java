package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.NotiMsg;
import lombok.Data;

@Data
public class NotiMsgDto {
    private long id;
    private char notiType;
    private String subject;
    private String content;
    private char sendType;
    private String sendStatus;

    public void fill(NotiMsg notiMsg) {
        id = notiMsg.getId();
        notiType = notiMsg.getNotiType();
        subject = notiMsg.getSubject();
        content = notiMsg.getContent();
        sendType = notiMsg.getSendType();
        sendStatus = notiMsg.getSendStatus();
    }
}
