package com.hanwha.tax.apiserver.entity;



import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "noti_msg")
public class NotiMsg extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="noti_type")
    private char notiType;

    @Column(length = 200)
    private String subject;

    @Column(length = 2000)
    private String content;

    @Column(name="send_type")
    private char sendType;

    @Column(name="send_status", length = 3)
    private String sendStatus;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
