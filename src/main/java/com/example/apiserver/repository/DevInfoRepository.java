package com.example.apiserver.repository;


import com.example.apiserver.entity.DevInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevInfoRepository extends JpaRepository<DevInfo, Long> {

}
