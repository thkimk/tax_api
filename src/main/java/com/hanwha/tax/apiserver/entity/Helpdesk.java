package com.hanwha.tax.apiserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.model.type.YesOrNo;
import com.hanwha.tax.apiserver.vo.AskVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "helpdesk")
public class Helpdesk {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String cid;

    @Column(name="ans_yn")
    private Character ansYn;

    @Column()
    private String ask_type;

    @Column()
    private String ask_subject;

    @Column()
    private String ask_content;

    @Column()
    private LocalDateTime ask_dt;

    public Helpdesk(AskVo askVo) {
        cid = askVo.getCid();

        ask_type = askVo.getType();
        ask_subject = askVo.getSubject();
        ask_content = askVo.getContent();
        ask_dt = LocalDateTime.now();

    }

}
