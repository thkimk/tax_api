package com.hanwha.tax.apiserver.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.Terms;
import com.hanwha.tax.apiserver.model.YesOrNo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TermsDetailDto {
    private long id;
    @JsonProperty("title")
    private String name;
    @JsonProperty("content")
    private String content;

    public void setTerms(Terms terms) {
        id = terms.getId();
        name = terms.getName();
        content = terms.getContent();
    }
}


