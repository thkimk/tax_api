package com.hanwha.tax.apiserver.entity;



import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "main_menu_banner")
public class MainMenuBanner extends TimeEntity {

    @Id
    @Column()
    private String banner_id;

    @Column()
    private String type;

    @Column()
    private String flag;

    @Column()
    private String title;

    @Column()
    private String subCopy;

    @Column()
    private String url;

    @Column()
    private Character urlType;

    @Column()
    private String imageUrl;

    @Column()
    private String buttonName;

    @Column()
    private LocalDateTime beginDt;

    @Column()
    private LocalDateTime endDt;

}
