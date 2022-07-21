package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import org.slf4j.MDC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "cust_nomember")
public class CustNomember {

    @Id
    @Column(name="dev_uid", length = 36)
    private String devUid;

    @Column()
    private String pushToken;

    @Column()
    private Character isAgree;

    @Column()
    private String osType;

    public CustNomember(NomemberVo nomemberVo) {
        this.devUid = MDC.get("uid");
        this.pushToken = nomemberVo.getPushToken();
        this.isAgree = nomemberVo.getIsAgree();
        this.osType = Utils.osType();

    }

}
