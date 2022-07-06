package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class MainMenuDto {

    @JsonProperty("expense_list")
    private List<Banner> expenseList;

    List<String> notice;

    @Data
    public static class Banner {
        String type;
        String subType;

        String title;
        String subCopy;
        String url;
        String urlType;
        String imageUrl;
        String buttonName;
    }

}
