package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustDeduct;
import com.hanwha.tax.apiserver.entity.CustFamily;
import com.hanwha.tax.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustDeductRepository extends JpaRepository<CustDeduct, Long> {

    CustDeduct findByCidAndYear(String cid, int year);

    @Query(value="select a.income from cust_deduct a where a.cust_id = :cid and a.year <= :year order by year desc limit 2" , nativeQuery=true)
    Long[] selectIncomes(@Param("cid") String cid, @Param("year") int year);

}
