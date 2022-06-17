package com.example.apiserver.repository;

import com.example.apiserver.entity.AuthInfo;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.entity.CustInfoDtl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustInfoDtlRepository extends JpaRepository<CustInfoDtl, Long> {
    CustInfoDtl findByCustId(String custId);

}
