package com.hanwha.tax.apiserver.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "book_income") // 'user' 테이블과 매핑됨을 명시한다.
public class BookIncome {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String custId;

    @Column(length = 10)
    private String orgCode;

    @Column()
    private String accountNum;

    @Column()
    private Long transAmt;

    @Column()
    private String currencyCode;

    @Column()
    private Character isIncome;

    @Column(name="is_33")
    private Character is33;

    @Column()
    private LocalDateTime transDtime;

    @Column()
    private LocalDateTime createDt;

}
