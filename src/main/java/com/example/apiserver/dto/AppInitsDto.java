package com.example.apiserver.dto;

import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.NotiMsg;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppInitsDto {
    List<AppInfoDto> appInfoDtos = new ArrayList<>();
    List<NotiMsgDto> notiMsgDtos = new ArrayList<>();

    public void fillAppInfos(List<AppInfo> appInfos) {
        for (AppInfo appInfo : appInfos) {
            AppInfoDto appInfoDto = new AppInfoDto();
            appInfoDto.fill(appInfo);

            appInfoDtos.add(appInfoDto);
        }
    }

    public void fillNotiMsgs(List<NotiMsg> notiMsgs) {
        for (NotiMsg notiMsg : notiMsgs) {
            NotiMsgDto notiMsgDto = new NotiMsgDto();
            notiMsgDto.fill(notiMsg);

            notiMsgDtos.add(notiMsgDto);
        }
    }

}
