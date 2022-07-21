package com.hanwha.tax.apiserver.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "mydata_income")
public class MydataIncome extends TimeEntity {

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


}
