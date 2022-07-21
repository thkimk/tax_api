package com.hanwha.tax.apiserver.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "main_menu_noti")
public class MainMenuNoti {

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


    @Column()
    private LocalDateTime updateDt;


}
