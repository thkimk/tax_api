package com.hanwha.tax.apiserver.repository;


import com.hanwha.tax.apiserver.entity.TotalIncome;
import com.hanwha.tax.apiserver.entity.TotalOutgoing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TotalOutgoingRepository extends JpaRepository<TotalOutgoing, Long> {
    List<TotalOutgoing> findByCustId(String custId);

    List<TotalOutgoing> findByYearOrderByMonthAsc(Integer year);
    List<TotalOutgoing> findByCustIdAndYear(String custId, Integer year);

    List<TotalOutgoing> findByYearAndMonth(Integer year, Integer month);
    List<TotalOutgoing> findByCustIdAndYearAndMonth(String custId, Integer year, Integer month);
}
