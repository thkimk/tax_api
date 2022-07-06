package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustRepository extends JpaRepository<Cust, Long> {
    Cust findByCustId(String custId);
//    Cust findOneOrderByCustIdAsc();

//    @Query(value="select a.cust_id from cust a order by a.cust_id desc limit 1" , nativeQuery=true)
    @Query(value="select a.cust_id from cust a where strcmp(DATE_FORMAT(now(), '%y%m'), left(a.cust_id,4)) = 0 order by a.cust_id desc limit 1" , nativeQuery=true)
    String getLastCustId();

    boolean existsByCustId(String custId);


}


