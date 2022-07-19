package com.hanwha.tax.apiserver.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanwha.tax.apiserver.advice.exception.InvalidInputValueException;
import com.hanwha.tax.apiserver.entity.Terms;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TermsDto {
    List<TermsDto.TermsVo> data = new ArrayList<>();

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class TermsVo {
        private long id;
        private String name;
        private String version;
        private String type;
        private YesOrNo viewYn;
        private String url;

        private List<TermsDto.TermsVo> child = null;

        public TermsVo(Terms terms) {
            id = terms.getId();
            name = terms.getName();
            type = terms.getType();
            url = terms.getUrl();
        }
    }


    public void fill(List<Terms> termsList) {
        TermsVo termsVo = null;

        for (Terms terms : termsList) {
            int typeLen = terms.getType().length();
            if (typeLen == 1) {
                termsVo = new TermsVo(terms);
                data.add(termsVo);
            } else if (typeLen == 2) {
                if (termsVo == null || !terms.getType().startsWith(termsVo.getType())) {
                    throw new InvalidInputValueException();
                }
                if (termsVo.getChild() == null) termsVo.setChild(new ArrayList<>());

                TermsVo termsVo2 = new TermsVo(terms);
                termsVo.getChild().add(termsVo2);
            } else {
            }
        }
    }
}
