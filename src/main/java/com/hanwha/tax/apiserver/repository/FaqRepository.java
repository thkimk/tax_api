package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Faq;
import com.hanwha.tax.apiserver.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FaqRepository extends JpaRepository<Faq, Long> {


}
