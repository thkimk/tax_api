package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustInfo;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustInfoRepository extends JpaRepository<CustInfo, Long> {

    CustInfo findByCid(String cid);

}
