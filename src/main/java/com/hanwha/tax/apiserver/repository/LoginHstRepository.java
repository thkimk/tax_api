package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Cust;
import com.hanwha.tax.apiserver.entity.LoginHst;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginHstRepository extends JpaRepository<LoginHst, Long> {

}


