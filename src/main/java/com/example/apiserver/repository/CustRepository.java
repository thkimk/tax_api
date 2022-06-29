package com.example.apiserver.repository;

import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustRepository extends JpaRepository<Cust, Long> {
    Cust findByCustId(String custId);
//    Cust findOneOrderByCustIdAsc();

    @Query(value="select a.cust_id from cust a order by a.cust_id desc limit 1" , nativeQuery=true)
    String getLastCustId();

    boolean existsByCustId(String custId);


}


