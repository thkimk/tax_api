package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustFamily;
import com.hanwha.tax.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustFamilyRepository extends JpaRepository<CustFamily, Long> {
    List<CustFamily> findAllByCid(String cid);
}
