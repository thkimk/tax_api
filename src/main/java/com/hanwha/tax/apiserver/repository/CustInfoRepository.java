package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustInfoRepository extends JpaRepository<CustInfo, Long> {
}
