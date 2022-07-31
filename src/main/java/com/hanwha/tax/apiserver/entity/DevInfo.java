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
@NoArgsConstructor
@Table(name = "dev_info")
public class DevInfo extends TimeEntity {
    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

//    @Id
    @Column(name="dev_uid", length = 36)
    private String devUid;

    @Column(name="push_token", length = 200)
    private String pushToken;


    @Column(name="dev_name", length = 50)
    private String devName;

    @Column(name="os_type", length = 10)
    private String osType;

    @Column(name="os_ver", length = 20)
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
