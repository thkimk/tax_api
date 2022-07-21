package com.hanwha.tax.apiserver.entity;



import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "main_menu")
@Where(clause="view_yn='Y'")
public class MainMenu extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
