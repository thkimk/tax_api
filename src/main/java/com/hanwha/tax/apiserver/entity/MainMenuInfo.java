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
@Table(name = "main_menu_info") // 'user' 테이블과 매핑됨을 명시한다.
public class MainMenuInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private long main_menu_id;

    @Column()
    private String custGrade;

    @Column()
    private String bannerId;

    @Column()
    private int order;

    @Column()
    private LocalDateTime createDt;

    @Column()
    private LocalDateTime updateDt;


}
