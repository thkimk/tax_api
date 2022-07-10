package com.hanwha.tax.apiserver.repository;


import com.hanwha.tax.apiserver.entity.Cust;
import com.hanwha.tax.apiserver.entity.DevInfo;
import com.hanwha.tax.apiserver.entity.TotalIncome;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TotalIncomeRepository extends JpaRepository<TotalIncome, Long> {

    List<TotalIncome> findByCustId(String custId);

    List<TotalIncome> findByYear(String year);
    List<TotalIncome> findByCustIdAndYear(String custId, String year);

    List<TotalIncome> findByYearAndMonth(String year, String month);
    List<TotalIncome> findByCustIdAndYearAndMonth(String custId, String year, String month);

}
