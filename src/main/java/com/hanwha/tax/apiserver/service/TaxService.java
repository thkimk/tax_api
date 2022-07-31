package com.hanwha.tax.apiserver.service;


import com.hanwha.tax.apiserver.model.Tax;
import com.hanwha.tax.apiserver.repository.TotalIncomeRepository;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TaxService {
    private final Tax tax;

    public Long predTax(Integer year) {
        tax.init(Utils.cid(), year);

        return tax.calRateTax();
    }


    public String simTax(SimTaxVo simTaxVo) {
        StringBuffer result = new StringBuffer();
        tax.init(simTaxVo, result);

        if (simTaxVo.getOutgoing().equals("0")) tax.calRateTax();
        else tax.calBookTax();

        return result.toString();
    }


}
