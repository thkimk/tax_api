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

    List<TotalIncome> findByYearOrderByMonthAsc(Integer year);
    List<TotalIncome> findByCustIdAndYear(String custId, Integer year);

    List<TotalIncome> findByYearAndMonth(Integer year, Integer month);
    List<TotalIncome> findByCustIdAndYearAndMonth(String custId, Integer year, Integer month);

    @Query(value="select sum(b.amount) from (" +
            "select a.trans_amt as amount from mydata_income a where a.cust_id = :cid and a.is_income = 'Y' and year(a.trans_dtime)=:year union " +
            "select a.trans_amt as amount from book_income a where a.cust_id = :cid and a.is_income = 'Y' and year(a.trans_dtime)=:year) b", nativeQuery=true)
    Long selectRtIncome(@Param("cid") String cid, @Param("year") int year);

    @Query(value="select sum(b.amount) from (" +
            "select a.appr_amt as amount from mydata_outgoing a where a.cust_id = :cid and year(a.appr_dtime)=:year union " +
            "select a.appr_amt as amount from book_outgoing a where a.cust_id = :cid and year(a.appr_dtime)=:year) b", nativeQuery=true)
    Long selectRtOutgoing(@Param("cid") String cid, @Param("year") int year);

    @Query(value="select sum(b.amount) from (" +
            "select a.trans_amt as amount from mydata_income a where a.cust_id = :cid and a.is_income = 'Y' and year(a.trans_dtime)=:year and a.is_33='Y' union " +
            "select a.trans_amt as amount from book_income a where a.cust_id = :cid and a.is_income = 'Y' and year(a.trans_dtime)=:year and a.is_33='Y') b", nativeQuery=true)
    Long selectRtIncome33(@Param("cid") String cid, @Param("year") int year);

}
