package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.NotiMsg;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;

@Data
public class NotiMsgDto {

    @JsonProperty("id")
    private long id;

    @JsonProperty("type")
    private Character type;

    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;

    @JsonProperty("date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime updateDt;

    public void setItem(NotiMsg notiMsg) {
        id = notiMsg.getId();
        type = notiMsg.getNotiType();
        title = notiMsg.getSubject();
        content = notiMsg.getContent();
        updateDt = notiMsg.getUpdatedDate();
    }
}
