package com.hanwha.tax.apiserver.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "terms")
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
