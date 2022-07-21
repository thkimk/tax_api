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

    @Column(length = 10)
    private String orgCode;

    @Column()
    private String cardId;

    @Column()
    private Integer seq;

    @Column()
    private String payType;

    @Column()
    private String status;

    @Column()
    private String merchantName;

    @Column()
    private String category;

    @Column()
    private String apprNum;

    @Column()
    private Long apprAmt;

    @Column()
    private Long modAmt;

    @Column()
    private LocalDateTime apprDtime;

    @Column()
    private LocalDateTime transDtime;

    @JsonIgnore
    @CreationTimestamp
    @Column(name="create_dt", updatable = false, nullable = false, columnDefinition = "datetime not null comment '생성일'")
    private LocalDateTime createdDate;
}
