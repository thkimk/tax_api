package com.example.apiserver.repository;

import com.example.apiserver.entity.NotiMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiMsgRepository extends JpaRepository<NotiMsg, Long> {
}
