package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustNomember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustNomemberRepository extends JpaRepository<CustNomember, Long> {
}
