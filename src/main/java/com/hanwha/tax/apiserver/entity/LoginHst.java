package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.vo.LoginVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "login_hst") // 'user' 테이블과 매핑됨을 명시한다.
public class LoginHst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 10)
    private String custId;

    @Column(length = 10)
    private String authType;

    @Column()
    private String devUid;

    @Column()
    private String devName;

    @Column()
    private String osName;

    @Column()
    private LocalDateTime loginDt;


    public LoginHst(LoginVo loginVo) {
        custId = loginVo.getCustId();
        authType = "PIN";
        loginDt = LocalDateTime.now();

        String[] devs = Utils.devs();
        this.devName = devs[1];
        this.osName = devs[2];
        this.devUid = MDC.get("uid");

    }

}
