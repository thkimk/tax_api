package com.example.apiserver.dto;

import com.example.apiserver.entity.Industry;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class IncomeDto {
    private String code;
    private String name;
    private BigDecimal simpleExrt;
    private BigDecimal simpleExrtExc;
    private BigDecimal standardExrt;
    private String searchTerms;

}
