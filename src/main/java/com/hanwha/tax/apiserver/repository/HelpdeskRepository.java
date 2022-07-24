package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.Faq;
import com.hanwha.tax.apiserver.entity.Helpdesk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HelpdeskRepository extends JpaRepository<Helpdesk, Long> {


}
