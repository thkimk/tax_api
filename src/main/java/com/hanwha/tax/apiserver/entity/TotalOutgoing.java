package com.hanwha.tax.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;


@Entity
@Table(name = "total_outgoing")
public class TotalOutgoing extends TimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name="cust_id", length = 10)
    @JsonIgnore
    private String custId;

    @Column()
    private int year;

    @Column()
    private int month;;


    @Column
    private Long amount;


    public TotalOutgoing(Integer year, Integer month, Long amount) {
        this.year = year;
        this.month = month;
        this.amount = amount;
    }
}
