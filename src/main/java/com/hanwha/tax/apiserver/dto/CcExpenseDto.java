package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CcExpenseDto {
    @JsonProperty("rsp_code")
    private String rspCode;

    @JsonProperty("rsp_message")
    private String rspMessage;

    @JsonProperty("expense_list_cnt")
    private Integer expenseListCnt;

    @JsonProperty("expense_list")
    private List<Expense> expenseList;

    @Data
    public static class Expense {
        String year;
        String month;
        Long total;
        String category;
        Integer count;
    }

}
