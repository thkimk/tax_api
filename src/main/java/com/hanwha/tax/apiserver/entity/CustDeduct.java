package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.entity.ids.CustDeductIds;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.slf4j.MDC;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "cust_deduct")
@IdClass(CustDeductIds.class)
public class CustDeduct {

    @Id
    @Column(name = "cust_id", length = 10, columnDefinition = "varchar(10) comment '회원번호'")
    private String cid;

    @Id
    @Column(name = "year", columnDefinition = "int comment '연도'")
    private int year;

    @Column(name = "income", columnDefinition = "bigint default 0 comment '수입'")
    private Long income;

    @Column(name = "npc_amt", columnDefinition = "bigint default 0 comment '국민연금보험료 납입액'")
    private Long npcAmount;

    @Column(name = "rsp_amt", columnDefinition = "bigint default 0 comment '연금저축 보유 납입액'")
    private Long rspAmount;

    @Column(name = "ira_amt", columnDefinition = "bigint default 0 comment '연금저축계좌보유 납입액'")
    private Long iraAmount;

    @Column(name = "med_amt", columnDefinition = "bigint default 0 comment '소상공인 공제부금 보유 납입액'")
    private Long medAmount;

    @Column(name = "sed_amt", columnDefinition = "bigint default 0 comment '중소기업창업투자 금액'")
    private Long sedAmount;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;

}
