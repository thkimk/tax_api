package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.entity.CustDeduct;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeductDto {
    private Long medAmount;
    private Long npcAmount;
    private Long sedAmount;
    private Long rspAmount;

    private Long iraAmount;

    public DeductDto(CustDeduct custDeduct) {
        medAmount = custDeduct.getMedAmount();
        npcAmount = custDeduct.getNpcAmount();
        sedAmount = custDeduct.getSedAmount();
        rspAmount = custDeduct.getRspAmount();

        iraAmount = custDeduct.getIraAmount();
    }

}
