package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.NotiSetting;
import com.hanwha.tax.apiserver.vo.CustTermsVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustTermsDto {
    private List<TermsVo> terms;

    private String sets;


    public CustTermsDto(List<CustTermsVo> custTermsVos, NotiSetting notiSetting) {
        terms = new ArrayList<>();
        custTermsVos.forEach(item -> {
            TermsVo termsVo = new TermsVo(item);
            terms.add(termsVo);
        });

        sets = String.format("%c%c%c%c", notiSetting.getEmailYn(), notiSetting.getPushYn(), notiSetting.getSmsYn(), notiSetting.getTalkYn());
    }

    @Data
    public static class TermsVo {
        String id;
        String type;

        public TermsVo(CustTermsVo custTermsVo) {
            id = custTermsVo.getId();
            type = custTermsVo.getType();

        }
    }

}
