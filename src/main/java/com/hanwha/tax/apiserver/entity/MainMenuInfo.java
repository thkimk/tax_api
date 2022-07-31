package com.hanwha.tax.apiserver.entity;


import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "main_menu_info")
public class MainMenuInfo extends TimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private long main_menu_id;

    @Column(name="cust_grade")
    private String custGrade;

    @Column(name="banner_id")
    private String bannerId;

    @Column()
    private int order;


}
