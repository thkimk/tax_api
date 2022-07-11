package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "dev_info") // 'user' 테이블과 매핑됨을 명시한다.
public class DevInfo {
    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

//    @Id
    @Column(length = 36)
    private String devUid;

    @Column(length = 200)
    private String pushToken;;


    @Column(length = 50)
    private String devName;

    @Column(length = 10)
    private String osType;

    @Column(length = 20)
    private String osVer;

    @Column()
    private LocalDateTime createDt;

    // 회원가입시 생성
    public DevInfo(SignupVo signupVo) {
        this.cid = signupVo.getCid();

        String[] devs = Utils.devs();
        this.devName = devs[1];
        this.osType = devs[2];
        this.osVer = devs[3];
        this.devUid = MDC.get("uid");
        this.pushToken = signupVo.getPushToken();

        this.createDt = LocalDateTime.now();

    }
}
