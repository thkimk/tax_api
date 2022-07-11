package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.LoginHst;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MydataIncomeRepository extends JpaRepository<MydataIncome, Long> {

}


