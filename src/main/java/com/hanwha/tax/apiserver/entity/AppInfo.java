package com.hanwha.tax.apiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "app_info")
public class AppInfo extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="os_name", length = 3)
    private String osName;

    @Column(name="rct_ver", length = 20)
    private String rctVer;

    @Column(name="upt_yn")
    private Character uptYn;

    @Column(name="apply_dt")
    private LocalDateTime applDt;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
