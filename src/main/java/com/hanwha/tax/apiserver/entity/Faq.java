package com.hanwha.tax.apiserver.entity;

import lombok.Getter;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "faq")
@Where(clause = "view_yn = 'Y'")
public class Faq  extends TimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "faq_type", length = 2, nullable = false)
    private String type;

    @Column(name = "view_yn", length = 1, nullable = false)
    private String viewYn;

    @Column(name = "subject", length = 200, nullable = false)
    private String question;

    @Column(name = "content", columnDefinition="TEXT")
    private String content;

    @Column(name = "creator", length = 50, nullable = false)
    private String creator;

    @Column(name = "updater", length = 50)
    private String updater;
}
