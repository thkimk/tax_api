package com.hanwha.tax.apiserver.repository;


import com.hanwha.tax.apiserver.entity.TotalIncome;
import com.hanwha.tax.apiserver.entity.TotalOutgoing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TotalOutgoingRepository extends JpaRepository<TotalOutgoing, Long> {
    List<TotalOutgoing> findByCustId(String custId);

    List<TotalOutgoing> findByYear(String year);
    List<TotalOutgoing> findByCustIdAndYear(String custId, String year);

    List<TotalOutgoing> findByYearAndMonth(String year, String month);
    List<TotalOutgoing> findByCustIdAndYearAndMonth(String custId, String year, String month);
}
