package com.example.apiserver.service;

import com.example.apiserver.Utils;
import com.example.apiserver.dto.AppInitsDto;
import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.entity.CustNomember;
import com.example.apiserver.entity.NotiMsg;
import com.example.apiserver.repository.AppInfoRepository;
import com.example.apiserver.repository.CustNomemberRepository;
import com.example.apiserver.repository.NotiMsgRepository;
import com.example.apiserver.vo.NomemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
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
    CustNomemberRepository custNomemberRepository;

    public AppInitsDto inits() {
        AppInitsDto appInitsDto = new AppInitsDto();

        // UA 파싱
        String osName = Utils.osType();

        // app_info 테이블
        AppInfo appInfo = appInfoRepository.findByOsName(osName);
        if (appInitsDto != null) {
            appInitsDto.fillAppInfos(appInfo);
        }

        // noti_msg 테이블
//        List<NotiMsg> notiMsgs = notiMsgRepository.findAll();
//        appInitsDto.fillNotiMsgs(notiMsgs);

        return appInitsDto;
    }


    public void nomember(NomemberVo nomemberVo) {
        if (nomemberVo.getIsAgree() == 'Y') {
            CustNomember custNomember = new CustNomember(nomemberVo);
            custNomemberRepository.save(custNomember);
        }
    }

}
