package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.NotiMsg;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiMsgRepository extends JpaRepository<NotiMsg, Long> {
}
