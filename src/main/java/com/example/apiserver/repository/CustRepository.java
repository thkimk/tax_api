package com.example.apiserver.repository;

import com.example.apiserver.entity.Cust;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustRepository extends JpaRepository<Cust, Long> {
}
