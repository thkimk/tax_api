package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.vo.BookIncomeVo;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "book_income")
@NoArgsConstructor
@Data
public class BookIncome extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String custId;

    @Column(name="trans_amt")
    private Long transAmt;

    @Column(name="is_33")
    private Character is33;

    @Column(name="trans_dtime")
    private LocalDateTime transDtime;


    public BookIncome(BookIncomeVo bookIncomeVo) {
        custId = bookIncomeVo.getCid();

        transAmt = bookIncomeVo.getAmount();
        is33 = bookIncomeVo.getIs33().toCharacter();
        transDtime = bookIncomeVo.getDtime();

    }

}
