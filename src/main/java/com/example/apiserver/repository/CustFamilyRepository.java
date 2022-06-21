package com.example.apiserver.repository;

import com.example.apiserver.entity.CustFamily;
import com.example.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustFamilyRepository extends JpaRepository<CustFamily, Long> {
}
