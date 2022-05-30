package com.example.apiserver.service;

import com.example.apiserver.dto.AppInitsDto;
import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.NotiMsg;
import com.example.apiserver.repository.AppInfoRepository;
import com.example.apiserver.repository.NotiMsgRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AppService {

    @Autowired
    AppInfoRepository appInfoRepository;

    @Autowired
    NotiMsgRepository notiMsgRepository;

    @Autowired
    public AppInitsDto appInits() {
        AppInitsDto appInitsDto = new AppInitsDto();

        // app_info 테이블
        List<AppInfo> appInfos = appInfoRepository.findAll();

        // noti_msg 테이블
        List<NotiMsg> notiMsgs = notiMsgRepository.findAll();

//        appInitsDto.setApp_infos(appInfos);
//        appInitsDto.setNoti_msgs(notiMsgs);

        return appInitsDto;
    }


}
