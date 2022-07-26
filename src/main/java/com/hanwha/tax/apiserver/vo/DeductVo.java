package com.hanwha.tax.apiserver.vo;

import com.hanwha.tax.apiserver.entity.CustDeduct;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class DeductVo {
    String cid;
    int year;

    private Long medAmount;
    private Long npcAmount;
    private Long sedAmount;
    private Long rspAmount;

    private Long iraAmount;
    
}
