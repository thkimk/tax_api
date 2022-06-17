package com.example.apiserver.repository;

import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.entity.CustNomember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustNomemberRepository extends JpaRepository<CustNomember, Long> {
}
