package com.example.apiserver.repository;

import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustInfoRepository extends JpaRepository<CustInfo, Long> {
}
