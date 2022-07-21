package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.UserDto;
import com.hanwha.tax.apiserver.dto.UserInterface;
import com.hanwha.tax.apiserver.entity.AuthInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AuthInfoRepository extends JpaRepository<AuthInfo, Long> {
    AuthInfo findByCid(String cid);


    @Query(value = "select a.cust_id from auth_info a where a.ci=:ci", nativeQuery = true)
    String selectCidByCi(@Param("ci") String ci);

    String findCidByCi(String ci);

    @Query(value = "select a.ci from auth_info a where a.cust_id=:cid", nativeQuery = true)
    String selectCiByCid(@Param("cid") String cid);

    @Query(value = "SELECT c.cust_id as cid, ci.name ,ci.email ,ci.birth , ci.mobile ,ci.telecom ,ci.gender , c.cust_grade as grade ,c.cust_status as status, ai.pin" +
            "  FROM cust c , cust_info ci , auth_info ai" +
            " WHERE ai.ci = :ci" +
            "   AND ai.cust_id  = c.cust_id  " +
            "   AND c.cust_id  = ci.cust_id", nativeQuery = true)
    UserInterface selectUserByCi(@Param("ci") String ci);

    @Query(value = "SELECT c.cust_id as cid, ci.name ,ci.email ,ci.birth , ci.mobile ,ci.telecom ,ci.gender , c.cust_grade as grade ,c.cust_status as status, ai.pin" +
            "  FROM cust c , cust_info ci , auth_info ai" +
            " WHERE ai.cust_id = :cid" +
            "   AND ai.cust_id  = c.cust_id  " +
            "   AND c.cust_id  = ci.cust_id", nativeQuery = true)
    UserInterface selectUserByCid(@Param("cid") String cid);
}
