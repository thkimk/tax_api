package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    // order by 필요 (order by type asc)
    List<Terms> findAllByViewYnOrderByType(char viewYn);

}
