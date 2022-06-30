package com.example.apiserver.dto;

import com.example.apiserver.entity.AppInfo;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class AppInfoDto {
    private String osName;
    private String recentVersion;
    private Character updateYn;
    private LocalDateTime applyDt;

    public void fill(AppInfo appInfo) {
        osName = appInfo.getOsName();
        recentVersion = appInfo.getRctVer();
        updateYn = appInfo.getUptYn();
        applyDt = appInfo.getApplDt();
    }
}
