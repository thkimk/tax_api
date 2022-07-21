package com.hanwha.tax.apiserver.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "terms") // 'user' 테이블과 매핑됨을 명시한다.
@Where(clause="view_yn='Y'")
public class Terms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(length = 5)
    private String version;

    @Column(length = 2)
    private String type;

    @Column()
    private char viewYn;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(length = 200)
    private String url;

    @Column()
    private LocalDateTime createDt;

    @Column(length = 50)
    private String creater;


}
