package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.AppInfo;
import com.hanwha.tax.apiserver.model.YesOrNo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppInfoDto {
    private String osName;
    private String recentVersion;
    private YesOrNo updateYn;
    private LocalDateTime applyDt;

    public void fill(AppInfo appInfo) {
        osName = appInfo.getOsName();
        recentVersion = appInfo.getRctVer();
        updateYn = YesOrNo.parse(appInfo.getUptYn());
        applyDt = appInfo.getApplDt();
    }
}
