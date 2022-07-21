package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.JobInterface;
import com.hanwha.tax.apiserver.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IndustryRepository extends JpaRepository<Industry, Long> {

    @Query(value = "SELECT code, name FROM industry", nativeQuery = true)
    List<JobInterface> selectAll();

    @Query(value = "SELECT code, name FROM industry WHERE code LIKE CONCAT('%',:code,'%')", nativeQuery = true)
    List<JobInterface> selectAllByCode(@Param("code") String code);

    @Query(value = "SELECT code, name FROM industry WHERE name LIKE CONCAT('%',:name,'%')", nativeQuery = true)
    List<JobInterface> selectAllByName(@Param("name") String name);

    Industry findOneByCode(String code);

}
