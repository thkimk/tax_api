package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.vo.BookIncomeVo;
import com.hanwha.tax.apiserver.vo.BookOutgoingVo;
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
@Table(name = "book_outgoing") // 'user' 테이블과 매핑됨을 명시한다.
public class BookOutgoing extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
    private String custId;

    @Column(name="merchant_name")
    private String merchantName;

    @Column()
    private String category;

    @Column(name="appr_amt")
    private Long apprAmt;

    @Column(name="appr_dtime")
    private LocalDateTime apprDtime;


    public BookOutgoing(BookOutgoingVo bookOutgoingVo) {
        custId = bookOutgoingVo.getCid();

        merchantName = bookOutgoingVo.getMerchantName();
        category = bookOutgoingVo.getCategory();
        apprAmt = bookOutgoingVo.getAmount();
        apprDtime = bookOutgoingVo.getDtime();

    }

}
