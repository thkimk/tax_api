package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.BookIncome;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BookIncomeRepository extends JpaRepository<BookIncome, Long> {

}


