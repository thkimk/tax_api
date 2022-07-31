package com.hanwha.tax.apiserver.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BookOutgoingVo {

    @JsonIgnore
    private String cid;

    private Long amount;
    private String merchantName;
    private String category;
    private LocalDateTime dtime;

}
