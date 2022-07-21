package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustTermsAgmt;
import com.hanwha.tax.apiserver.entity.ids.CustTermsAgmtIds;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustTermsAgmtRepository extends JpaRepository<CustTermsAgmt, CustTermsAgmtIds> {

}
