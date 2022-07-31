package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.vo.SaveAuthVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "auth_info")
@NoArgsConstructor
@Data
public class AuthInfo extends TimeEntity {
    @Id
    @Column(name = "cust_id", length = 10)
    private String cid;

    @Column(length = 256)
    private String pin;

    @Column(length = 100)
    private String ci;

    @Column(name = "is_main")
    private Character isMain;

    @Column(name = "auth_status", length = 2)
    private String authStatus;

    @Column(name = "fail_cnt")
    private int failCnt;


    public AuthInfo(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        this.pin = signupVo.getPin();
        this.ci = signupVo.getCi();
    }


    public void fill(SaveAuthVo saveAuthVo) {
        this.pin = saveAuthVo.getPin();
    }

}
