package com.hanwha.tax.apiserver.entity;


import com.hanwha.tax.apiserver.vo.SaveAuthVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Getter //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "noti_setting") // 'user' 테이블과 매핑됨을 명시한다.
public class NotiSetting {
    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Column(name="push_yn")
    private Character pushYn;

    @Column(name="sms_yn")
    private Character smsYn;

    @Column(name="email_yn")
    private Character emailYn;

    @Column(name="talk_yn")
    private Character talkYn;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    public NotiSetting(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        String notiAgmts = signupVo.getAgreeTerms().split(";")[1];
        pushYn = notiAgmts.charAt(0);
        smsYn = notiAgmts.charAt(1);
        emailYn = notiAgmts.charAt(2);
        talkYn = notiAgmts.charAt(3);

        this.updateDt = LocalDateTime.now();

    }


}
