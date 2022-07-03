package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustFamily;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustFamilyRepository extends JpaRepository<CustFamily, Long> {
}
