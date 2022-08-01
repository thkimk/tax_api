package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustTermsAgmt;
import com.hanwha.tax.apiserver.entity.ids.CustTermsAgmtIds;
import com.hanwha.tax.apiserver.vo.CustTermsVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CustTermsAgmtRepository extends JpaRepository<CustTermsAgmt, CustTermsAgmtIds> {

    @Query(value="select a.terms_id as id, b.type as type from cust_terms_agmt a inner join terms b " +
            "on a.terms_id = b.id " +
            "where a.cust_id = :cid and b.view_yn = 'Y' and cast(b.type as UNSIGNED) >= 10 " , nativeQuery=true)
    List<CustTermsVo> selectCustTerms(@Param("cid") String cid);

}
