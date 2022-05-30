package com.example.apiserver.repository;

import com.example.apiserver.entity.AppInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppInfoRepository extends JpaRepository<AppInfo, Long> {
}
