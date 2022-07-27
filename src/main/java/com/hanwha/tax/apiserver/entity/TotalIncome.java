package com.hanwha.tax.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Getter
@Entity
@NoArgsConstructor
@Table(name = "total_income")
public class TotalIncome extends TimeEntity{
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
    private int month;

    @Column
    private Long amount;

    public TotalIncome(Integer year, Integer month, Long amount) {
        this.year = year;
        this.month = month;
        this.amount = amount;
    }

}
