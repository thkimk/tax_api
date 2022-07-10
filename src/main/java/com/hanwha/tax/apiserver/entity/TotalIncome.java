package com.hanwha.tax.apiserver.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.*;
import java.time.LocalDateTime;


@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "total_income") // 'user' 테이블과 매핑됨을 명시한다.
public class TotalIncome {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name="cust_id", length = 10)
    @JsonIgnore
    private String custId;

    @Column(length = 2)
    private String year;

    @Column(length = 2)
    private String month;

    @Column
    private Long amount;

    @Column()
    @JsonIgnore
    private LocalDateTime createDt;

    @Column()
    @JsonIgnore
    private LocalDateTime updateDt;

}
