package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    AuthInfo findByCid(String cid);


    @Query(value="select a.cust_id from auth_info a where a.ci=:ci" , nativeQuery=true)
    String selectCidByCi(@Param("ci") String ci);
    String findCidByCi(String ci);

    @Query(value="select a.ci from auth_info a where a.cust_id=:cid" , nativeQuery=true)
    String selectCiByCid(@Param("cid") String cid);

}
