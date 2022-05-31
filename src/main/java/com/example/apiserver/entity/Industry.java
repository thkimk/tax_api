package com.example.apiserver.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "industry") // 'user' 테이블과 매핑됨을 명시한다.
public class Industry {

    @Id
    @Column(length = 6)
    private String code;

    @Column(length = 200)
    private String name;

    @Column()
    private BigDecimal simple_exrt;

    @Column()
    private BigDecimal simple_exrt_exc;

    @Column()
    private BigDecimal standard_exrt;

    @Column(length = 200)
    private String search_terms;

    @Column()
    private LocalDateTime create_dt;

    @Column()
    private LocalDateTime update_dt;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
