package com.hanwha.tax.apiserver.entity;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_income")
@Data
public class BookIncome extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String custId;

    @Column()
    private Long transAmt;

    @Column(name="is_33")
    private Character is33;

    @Column()
    private LocalDateTime transDtime;

}
