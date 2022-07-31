package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.AnswerDto;
import com.hanwha.tax.apiserver.entity.Faq;
import com.hanwha.tax.apiserver.entity.Helpdesk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface HelpdeskRepository extends JpaRepository<Helpdesk, Long> {

    @Query(value="select a.ask_type as `type`, a.ask_subject as subject, a.ask_content as content, b.ans_content as answer " +
                    "from helpdesk a, helpdesk_ans b " +
                    "where a.id = b.helpdesk_id and a.cust_id = :cid",
            countQuery = "select count(*) from helpdesk where cust_id = :cid",
            nativeQuery=true)
    Page<AnswerDto> selectAnswer(@Param("cid") String cid, Pageable pageable);
}
