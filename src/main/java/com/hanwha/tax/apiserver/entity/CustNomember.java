package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "cust_nomember") // 'user' 테이블과 매핑됨을 명시한다.
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
