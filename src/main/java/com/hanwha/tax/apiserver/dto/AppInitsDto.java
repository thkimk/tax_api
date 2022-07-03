package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.AppInfo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppInitsDto {
    private String osName;
    private String recentVersion;
    private Character updateYn;
    private LocalDateTime applyDt;


    public void fillAppInfos(AppInfo appInfo) {
        osName = appInfo.getOsName();
        recentVersion = appInfo.getRctVer();
        updateYn = appInfo.getUptYn();
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
