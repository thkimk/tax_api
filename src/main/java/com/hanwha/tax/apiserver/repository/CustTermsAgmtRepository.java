package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.UserDto;
import com.hanwha.tax.apiserver.entity.Cust;
import com.hanwha.tax.apiserver.entity.CustTermsAgmt;
import com.hanwha.tax.apiserver.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustTermsAgmtRepository extends JpaRepository<CustTermsAgmt, Long> {

}
