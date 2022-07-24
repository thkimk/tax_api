package com.hanwha.tax.apiserver.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class AskVo {
//    @JsonIgnore
    String cid;

    String type;
    String subject;
    String content;
}
