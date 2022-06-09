package com.example.apiserver.entity;


import com.example.apiserver.vo.SaveAuthVo;
import com.example.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "auth_info") // 'user' 테이블과 매핑됨을 명시한다.
public class AuthInfo {
    @Id
    @Column(name="cust_id", length = 9)
    private String custId;

    @Column(length = 256)
    private String pin;

    @Column(length = 100)
    private String ci;

    @Column(name="is_main")
    private char isMain;

    @Column(name="auth_status", length = 2)
    private String authStatus;

    @Column(name="fail_cnt")
    private int failCnt;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    public AuthInfo(SignupVo signupVo, String custId) {
        this.custId = custId;
        this.pin = signupVo.getPin();
        this.ci = signupVo.getCi();

        this.createDt = LocalDateTime.now();

    }


    public AuthInfo(SaveAuthVo saveAuthVo) {
        this.custId = saveAuthVo.getCustId();
        this.pin = saveAuthVo.getPin();
        this.ci = saveAuthVo.getCi();

        this.updateDt = LocalDateTime.now();
        this.createDt = LocalDateTime.now();

    }

}
