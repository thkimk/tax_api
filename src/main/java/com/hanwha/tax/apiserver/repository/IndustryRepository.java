package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {
    List<Industry> findAllByCode(String code);

    List<Industry> findAllByName(String name);

    Industry findOneByCode(String code);

}
