package com.example.apiserver.dto;

import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.NotiMsg;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
