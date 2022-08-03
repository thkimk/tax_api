package com.hanwha.tax.apiserver.model;


import com.hanwha.tax.apiserver.entity.CustFamily;
import lombok.Data;

import java.util.List;

@Data
public class SimTax {
    String taxFlag = "";
    String jobCode = "";
    String preIncome="0", income="0", outgoing="0";

    String isOrder="N", isSinParent="N", isMarriage="N", isHshld="N";
    String gender = "M";
    String birth = "";

    String npc="0", med="0", sed="0", rsp="0", ira="0";
    List<CustFamily> custFamilyList = null;

    String earning="0";
    String deduct="0", deductMe="0", deductFamily="0", deductOthers="0";
    String taxBase="0";
    String calTax="0", taxRate="0";
    String decTax="0", taxDeduct="0";
    String finTax="0", addTax="0", paidTax="0";

    public SimTax(Tax tax) {
        taxFlag = Tax.simTaxFlag(tax.getTaxFlag());
        jobCode = tax.getIndustry().getCode()+ "/ "+ tax.getIndustry().getName();
        preIncome = String.format("%,d", tax.getIncomes()[0]);
        income = String.format("%,d", tax.getIncomes()[1]);
        outgoing = String.format("%,d", tax.getOutgoing());

        isOrder = String.valueOf(tax.getCustInfoDtl().getIsDisorder());
        isSinParent = String.valueOf(tax.getCustInfoDtl().getIsSinParent());
        isMarriage = String.valueOf(tax.getCustInfoDtl().getIsMarriage());
        isHshld = String.valueOf(tax.getCustInfoDtl().getIsHshld());
        gender = String.valueOf(tax.getCustInfo().getGender());
        birth = tax.getCustInfo().getBirth();

        rsp = String.format("%,d", tax.getCustDeduct().getRspAmount());
        npc = String.format("%,d", tax.getCustDeduct().getNpcAmount());
        med = String.format("%,d", tax.getCustDeduct().getMedAmount());
        sed = String.format("%,d", tax.getCustDeduct().getSedAmount());

        ira = String.format("%,d", tax.getCustDeduct().getIraAmount());
        custFamilyList = tax.getCustFamilyList();

        earning = String.format("%,d", tax.getIncomes()[1] - tax.getOutgoing());
        deduct = String.format("%,d", tax.getDeduct());
        deductMe = String.format("%,d", tax.getDeductMe());
        deductFamily = String.format("%,d", tax.getDeductFamily());
        deductOthers = String.format("%,d", tax.getDeductOthers());
    }
}
