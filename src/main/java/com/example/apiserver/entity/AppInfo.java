package com.example.apiserver.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // jpa entity 임을 알린다.
@Data //user 필드 값의 getter를 자동생성한다.
@Builder // builder를 사용할 수 있게 한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "app_info") // 'user' 테이블과 매핑됨을 명시한다.
public class AppInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="os_name", length = 3)
    private String osName;

    @Column(name="rct_ver", length = 20)
    private String rctVer;

    @Column(name="upt_yn")
    private char uptYn;

    @Column(name="apply_dt")
    private LocalDateTime applDt;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;

    @Column(length = 50)
    private String creater;

    @Column(length = 50)
    private String updater;

}
