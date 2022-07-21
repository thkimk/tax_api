package com.hanwha.tax.apiserver.vo;

import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

@Data
public class SaveJobVo {
    String cid;
    YesOrNo isNewBusin;    // 신규사업자면 Y, 아니면 N
    String jobCode;     // 업종코드
    String income;      // 직전년도 소득
}
