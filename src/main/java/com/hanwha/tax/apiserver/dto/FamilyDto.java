package com.hanwha.tax.apiserver.dto;

import com.hanwha.tax.apiserver.entity.CustDeduct;
import com.hanwha.tax.apiserver.entity.CustFamily;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;


@Data
@NoArgsConstructor
public class FamilyDto {
    private String family;

    private String birth;

    private YesOrNo isDisorder;

    public FamilyDto(CustFamily custFamily) {
        family = custFamily.getFamily();
        birth = custFamily.getBirth();
        isDisorder = YesOrNo.parse(custFamily.getIsDisorder());
    }

}
