package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "cust_nomember")
public class CustNomember {

    @Id
    @Column(name="dev_uid", length = 36)
    private String devUid;

    @Column(name="push_token")
    private String pushToken;

    @Column(name="is_agree")
    private Character isAgree;

    @Column(name="os_type")
    private String osType;

    public CustNomember(NomemberVo nomemberVo) {
        this.devUid = MDC.get("uid");
        this.pushToken = nomemberVo.getPushToken();
        this.isAgree = nomemberVo.getIsAgree();
        this.osType = Utils.osType();

    }

}
