package com.hanwha.tax.apiserver.entity;


import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "main_menu_noti")
@Where(clause="use_yn='Y'")
public class MainMenuNoti extends TimeEntity {

    @Id
    @Column()
    private String notiId;

    @Column()
    private Character useYn;

    @Column()
    private String content;

    @Column()
    private LocalDateTime beginDt;

    @Column()
    private LocalDateTime endDt;

}
