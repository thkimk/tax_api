package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.AppInfo;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppInitsDto {
    @JsonProperty("recentVersion")
    private String recentVersion;

    @JsonProperty("updateYn")
    private YesOrNo updateYn;

    @JsonProperty("applyDt")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime applyDt;


    public void fillAppInfos(AppInfo appInfo) {
        recentVersion = appInfo.getRctVer();
        updateYn = YesOrNo.parse(appInfo.getUptYn());
        applyDt = appInfo.getApplDt();
    }

/*
    List<NotiMsgDto> notiMsgDtos = new ArrayList<>();
    public void fillNotiMsgs(List<NotiMsg> notiMsgs) {
        for (NotiMsg notiMsg : notiMsgs) {
            NotiMsgDto notiMsgDto = new NotiMsgDto();
            notiMsgDto.fill(notiMsg);

            notiMsgDtos.add(notiMsgDto);
        }
    }
*/

}
