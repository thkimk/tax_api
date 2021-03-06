package com.hanwha.tax.apiserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class TotalIncomeDto {
    private List<Income> incomes;

    @Data
    public static class Income {
        String year;
        String month;
        Long amount;
    }

}
