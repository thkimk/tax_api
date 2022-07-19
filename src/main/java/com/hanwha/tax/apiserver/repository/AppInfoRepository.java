package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {
    @Query(value = "select * from app_info where os_name = 'AOS' and apply_dt <= CURRENT_DATE order by `apply_dt` desc limit 1", nativeQuery = true)
    AppInfo findByOsName(String osName);

}
