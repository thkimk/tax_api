package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {
    AppInfo findByOsName(String osName);

}
