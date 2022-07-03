package com.hanwha.tax.apiserver.repository;


import com.hanwha.tax.apiserver.entity.DevInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevInfoRepository extends JpaRepository<DevInfo, Long> {

}
