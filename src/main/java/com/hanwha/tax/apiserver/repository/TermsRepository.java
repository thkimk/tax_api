package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    @Query(value = "select *            " +
            "       from terms          " +
            "       where view_yn = 'Y' " +
            "       order by type", nativeQuery = true)
    List<Terms> findAllOrderByType();


    Optional<Terms> findById(Long id);

}
