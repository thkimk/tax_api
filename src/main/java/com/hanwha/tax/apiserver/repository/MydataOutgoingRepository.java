package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.MydataIncome;
import com.hanwha.tax.apiserver.entity.MydataOutgoing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MydataOutgoingRepository extends JpaRepository<MydataOutgoing, Long> {

}


