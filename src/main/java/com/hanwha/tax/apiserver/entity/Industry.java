package com.hanwha.tax.apiserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "industry")
public class Industry extends TimeEntity {

    @Id
    @Column(length = 6)
    private String code;

    @Column(length = 200)
    private String name;

    @JsonIgnore
    @Column(name="simple_exrt")
    private BigDecimal simpleExrt;

    @JsonIgnore
    @Column(name="simple_exrt_exc")
    private BigDecimal simpleExrtExc;

    @JsonIgnore
    @Column(name="standard_exrt")
    private BigDecimal standardExrt;

    @JsonIgnore
    @Column(name="search_terms", length = 200)
    private String searchTerms;

    @JsonIgnore
    @Column(length = 50)
    private String creater;

    @JsonIgnore
    @Column(length = 50)
    private String updater;

}
