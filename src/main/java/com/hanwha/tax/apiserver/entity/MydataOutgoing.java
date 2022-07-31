package com.hanwha.tax.apiserver.entity;



import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mydata_outgoing")
public class MydataOutgoing  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String custId;

    @Column(name="org_code", length = 10)
    private String orgCode;

    @Column(name="card_id")
    private String cardId;

    @Column()
    private Integer seq;

    @Column(name="pay_type")
    private String payType;

    @Column()
    private String status;

    @Column(name="merchant_name")
    private String merchantName;

    @Column()
    private String category;

    @Column(name="appr_num")
    private String apprNum;

    @Column(name="appr_amt")
    private Long apprAmt;

    @Column(name="mod_amt")
    private Long modAmt;

    @Column(name="appr_dtime")
    private LocalDateTime apprDtime;

    @Column(name="trans_dtime")
    private LocalDateTime transDtime;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="create_dt", updatable = false, nullable = false, columnDefinition = "datetime not null comment '생성일'")
    private LocalDateTime createdDate;
}
