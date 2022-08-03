package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustFamily;
import com.hanwha.tax.apiserver.entity.CustInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustFamilyRepository extends JpaRepository<CustFamily, Long> {
    List<CustFamily> findAllByCid(String cid);

//    @Modifying(clearAutomatically = true, flushAutomatically = true)
//    @Query(value="delete from cust_family a where a.cust_id = :cid", nativeQuery = true)
//    void removeByCid(@Param("cid") String cid);

}
