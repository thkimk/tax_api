package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Value;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CcIncomeDto {
    @JsonProperty("rsp_code")
    private String rspCode;

    @JsonProperty("rsp_message")
    private String rspMessage;

    @JsonProperty("incomes_list_cnt")
    private Integer incomesListCnt;

    @JsonProperty("incomes_list")
    private List<Income> incomesList;

    @Data
    public static class Income {
        String year;
        String month;
        Long total;
        Character tyle;
        Integer count;
    }

}
