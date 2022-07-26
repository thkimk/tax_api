package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.LoginVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "dev_info")
@NoArgsConstructor
public class DevInfo extends TimeEntity {
    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

//    @Id
    @Column(length = 36)
    private String devUid;

    @Column(length = 200)
    private String pushToken;


    @Column(length = 50)
    private String devName;

    @Column(length = 10)
    private String osType;

    @Column(length = 20)
    private String osVer;

    // 회원가입시 생성
    public DevInfo(SignupVo signupVo) {
        this.cid = signupVo.getCid();

        String[] devs = Utils.devs();
        this.devName = devs[1];
        this.osType = devs[2];
        this.osVer = devs[3];
        this.devUid = MDC.get("uid");
        this.pushToken = signupVo.getPushToken();
    }

    public DevInfo(LoginVo loginVo) {
        this.cid = loginVo.getCid();

        String[] devs = Utils.devs();
        this.devName = devs[1];
        this.osType = devs[2];
        this.osVer = devs[3];
        this.devUid = MDC.get("uid");
        this.pushToken = loginVo.getPushToken();
    }
}
