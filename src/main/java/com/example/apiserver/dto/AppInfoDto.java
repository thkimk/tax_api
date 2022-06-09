package com.example.apiserver.dto;

import com.example.apiserver.entity.AppInfo;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class AppInfoDto {
    private long id;
    private String osName;
    private String rctVer;
    private char uptYn;
    private LocalDateTime applDt;

    public void fill(AppInfo appInfo) {
        id = appInfo.getId();
        osName = appInfo.getOsName();
        rctVer = appInfo.getRctVer();
        uptYn = appInfo.getUptYn();
        applDt = appInfo.getApplDt();
    }
}
