package com.hanwha.tax.apiserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mydata_income")
public class MydataIncome  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "cust_id", length = 10)
    private String custId;

    @Column(name="org_code", length = 10)
    private String orgCode;

    @Column(name="account_num")
    private String accountNum;

    @Column()
    private Integer seq;

    @Column(name="trans_amt")
    private Long transAmt;

    @Column(name="trans_type")
    private String transType;

    @Column(name="trans_class")
    private String transClass;

    @Column(name="currency_code")
    private String currencyCode;

    @Column(name="is_income")
    private Character isIncome;

    @Column(name = "is_33")
    private Character is33;

    @Column(name="trans_dtime")
    private LocalDateTime transDtime;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="create_dt", updatable = false, nullable = false, columnDefinition = "datetime not null comment '생성일'")
    private LocalDateTime createdDate;


}
