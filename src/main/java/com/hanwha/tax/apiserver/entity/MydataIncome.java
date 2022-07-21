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

    @Column(length = 10)
    private String orgCode;

    @Column()
    private String accountNum;

    @Column()
    private Integer seq;

    @Column()
    private Long transAmt;

    @Column()
    private String transType;

    @Column()
    private String transClass;

    @Column()
    private String currencyCode;

    @Column()
    private Character isIncome;

    @Column(name = "is_33")
    private Character is33;

    @Column()
    private LocalDateTime transDtime;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="create_dt", updatable = false, nullable = false, columnDefinition = "datetime not null comment '생성일'")
    private LocalDateTime createdDate;


}
