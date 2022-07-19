package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.BookOutgoing;
import com.hanwha.tax.apiserver.entity.MydataOutgoing;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookOutgoingRepository extends JpaRepository<BookOutgoing, Long> {

}


