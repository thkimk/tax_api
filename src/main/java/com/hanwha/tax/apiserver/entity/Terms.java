package com.hanwha.tax.apiserver.entity;


import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "terms")
@Where(clause="view_yn='Y'")
public class Terms extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String name;

    @Column(length = 5)
    private String version;

    @Column(length = 2)
    private String type;

    @Column(name="view_yn")
    private char viewYn;

    @Column(columnDefinition="TEXT")
    private String content;

    @Column(length = 200)
    private String url;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;
}
