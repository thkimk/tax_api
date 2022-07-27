package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.BookIncome;
import com.hanwha.tax.apiserver.entity.MydataIncome;
import com.hanwha.tax.apiserver.entity.TotalIncome;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookIncomeRepository extends JpaRepository<BookIncome, Long> {
    @Query(value = "select * from book_income a where a.cust_id =:cid and year(a.trans_dtime)=:year and month(a.trans_dtime)=:month order by a.trans_dtime desc", nativeQuery = true)
    List<BookIncome> selectByYearAndMonth(@Param("cid") String cid, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from book_income a where a.cust_id =:cid and year(a.trans_dtime)=:year order by a.trans_dtime desc", nativeQuery = true)
    List<BookIncome> selectByYear(@Param("cid") String cid, @Param("year") Integer year);

    List<BookIncome> findByCustIdOrderByTransDtimeDesc(String custId);

}


