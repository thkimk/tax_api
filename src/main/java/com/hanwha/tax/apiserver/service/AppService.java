package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.dto.AppInitsDto;
import com.hanwha.tax.apiserver.entity.AppInfo;
import com.hanwha.tax.apiserver.entity.CustNomember;
import com.hanwha.tax.apiserver.repository.AppInfoRepository;
import com.hanwha.tax.apiserver.repository.CustNomemberRepository;
import com.hanwha.tax.apiserver.repository.NotiMsgRepository;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
