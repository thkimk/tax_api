package com.hanwha.tax.apiserver.service;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxService {
    public Long predTax(Integer year) {
        Long tax = calTax("cid", 2022, 11);
        return tax;
    }


    public Long calTax(String cid, int year, int taxFlag) {
        Long tax = Long.valueOf(0);

        return tax;
    }

}
