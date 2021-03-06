package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.LoginHst;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MydataIncomeRepository extends JpaRepository<MydataIncome, Long> {

}


