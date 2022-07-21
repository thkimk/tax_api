package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustDeduct;
import com.hanwha.tax.apiserver.entity.CustDeductIds;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustDeductRepository extends JpaRepository<CustDeduct, CustDeductIds> {

    CustDeduct findByCidAndYear(String cid, int year);

    @Query(value = "SELECT * " +
            "  FROM cust_deduct" +
            "  WHERE cust_id = :cid" +
            "    AND year  = :year", nativeQuery = true)
    CustDeduct selectByIds(@Param("cid") String cid, @Param("year") int year);

    @Query(value = "select a.income from cust_deduct a where a.cust_id = :cid and a.year <= :year order by year desc limit 2", nativeQuery = true)
    Long[] selectIncomes(@Param("cid") String cid, @Param("year") int year);

}
