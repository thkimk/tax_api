package com.example.apiserver.repository;

import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.CustInfoDtl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {
    AppInfo findByOsName(String osName);

}
