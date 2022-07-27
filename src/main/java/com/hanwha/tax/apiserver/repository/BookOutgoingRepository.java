package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.BookIncome;
import com.hanwha.tax.apiserver.entity.BookOutgoing;
import com.hanwha.tax.apiserver.entity.MydataOutgoing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookOutgoingRepository extends JpaRepository<BookOutgoing, Long> {

    @Query(value = "select * from book_outgoing a where a.cust_id =:cid and year(a.appr_dtime)=:year and month(a.appr_dtime)=:month order by a.appr_dtime desc", nativeQuery = true)
    List<BookOutgoing> selectByYearAndMonth(@Param("cid") String cid, @Param("year") Integer year, @Param("month") Integer month);

    @Query(value = "select * from book_outgoing a where a.cust_id =:cid and year(a.appr_dtime)=:year order by a.appr_dtime desc", nativeQuery = true)
    List<BookOutgoing> selectByYear(@Param("cid") String cid, @Param("year") Integer year);

    List<BookOutgoing> findByCustIdOrderByApprDtimeDesc(String custId);

}


