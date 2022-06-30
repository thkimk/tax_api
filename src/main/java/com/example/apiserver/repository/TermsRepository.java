package com.example.apiserver.repository;

import com.example.apiserver.entity.Industry;
import com.example.apiserver.entity.Terms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TermsRepository extends JpaRepository<Terms, Long> {

    List<Terms> findAllByViewYn(char viewYn);

}
