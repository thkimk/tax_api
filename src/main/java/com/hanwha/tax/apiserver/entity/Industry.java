package com.hanwha.tax.apiserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "industry")
public class Industry {

    @Id
    @Column(length = 6)
    private String code;

    @Column(length = 200)
    private String name;

    @JsonIgnore
    @Column()
    private BigDecimal simpleExrt;

    @JsonIgnore
    @Column()
    private BigDecimal simpleExrtExc;

    @JsonIgnore
    @Column()
    private BigDecimal standardExrt;

    @JsonIgnore
    @Column(length = 200)
    private String searchTerms;

    @JsonIgnore
    @Column()
    private LocalDateTime createDt;

    @JsonIgnore
    @Column()
    private LocalDateTime updateDt;

    @JsonIgnore
    @Column(length = 50)
    private String creater;

    @JsonIgnore
    @Column(length = 50)
    private String updater;

}
