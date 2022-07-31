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

    @Column(name="sub_copy")
    private String subCopy;

    @Column()
    private String url;

    @Column(name="url_type")
    private Character urlType;

    @Column(name="image_url")
    private String imageUrl;

    @Column(name="button_name")
    private String buttonName;

    @Column(name="begin_dt")
    private LocalDateTime beginDt;

    @Column(name="end_dt")
    private LocalDateTime endDt;

}
