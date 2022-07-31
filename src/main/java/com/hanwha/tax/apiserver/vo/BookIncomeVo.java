package com.hanwha.tax.apiserver.vo;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.entity.TimeEntity;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
public class BookIncomeVo {

    @JsonIgnore
    private String cid;

    private Long amount;
    private YesOrNo is33;
    private LocalDateTime dtime;

}
