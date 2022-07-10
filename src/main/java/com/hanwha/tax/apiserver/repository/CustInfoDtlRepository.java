package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustInfoDtlRepository extends JpaRepository<CustInfoDtl, Long> {
    CustInfoDtl findByCid(String cid);

}
