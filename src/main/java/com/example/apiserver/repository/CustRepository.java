package com.example.apiserver.repository;

import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.Industry;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustRepository extends JpaRepository<Cust, Long> {
    Cust findByCustId(String custId);

}
