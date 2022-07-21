package com.hanwha.tax.apiserver.entity;



import javax.persistence.*;

@Entity
@Table(name = "noti_msg")
public class NotiMsg extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column()
    private char notiType;

    @Column(length = 200)
    private String subject;

    @Column(length = 2000)
    private String content;

    @Column()
    private char sendType;

    @Column(length = 3)
    private String sendStatus;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
