package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.Industry;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class JobsDto {
    private String code;
    private String name;
    private BigDecimal simpleExrt;
    private BigDecimal simpleExrtExc;
    private BigDecimal standardExrt;
    private String searchTerms;

    public void fill(Industry industry) {
        code = industry.getCode();
        name = industry.getName();
        simpleExrt = industry.getSimpleExrt();
        simpleExrtExc = industry.getSimpleExrtExc();
        standardExrt = industry.getStandardExrt();
        searchTerms = industry.getSearchTerms();

    }
}
