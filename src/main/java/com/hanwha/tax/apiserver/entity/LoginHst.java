package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.LoginVo;
import org.slf4j.MDC;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity // jpa entity 임을 알린다.
@Table(name = "login_hst") // 'user' 테이블과 매핑됨을 명시한다.
public class LoginHst {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="cust_id", length = 10)
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
        custId = loginVo.getCid();
        authType = "PIN";
        loginDt = LocalDateTime.now();

        String[] devs = Utils.devs();
        this.devName = devs[1];
        this.osName = devs[2];
        this.devUid = MDC.get("uid");

    }

}
