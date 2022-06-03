package com.example.apiserver.repository;

import com.example.apiserver.entity.AppInfo;
import com.example.apiserver.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    AuthInfo findByCustId(String custId);
}
