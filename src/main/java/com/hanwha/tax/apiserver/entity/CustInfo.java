package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.vo.SaveAuthVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
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
@Table(name = "cust_info") // 'user' 테이블과 매핑됨을 명시한다.
public class CustInfo {

    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Column(length = 50)
    private String name;

    @Column(length = 256)
    private String email;

    @Column(length = 8)
    private String birth;

    @Column(length = 15)
    private String mobile;

    @Column()
    private String telecom;

    @Column()
    private Character gender;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    // 회원가입시 생성
    public CustInfo(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        this.name = signupVo.getName();
        this.email = signupVo.getEmail();
        this.birth = signupVo.getBirth();
        this.mobile = signupVo.getMobile();
        this.telecom = signupVo.getTelecom();
        this.gender = signupVo.getGender().toCharacter();
        this.createDt = LocalDateTime.now();

    }

    public CustInfo(SaveAuthVo saveAuthVo) {
        this.cid = Utils.cid();
        this.name = saveAuthVo.getName();
        this.email = saveAuthVo.getEmail();
        this.birth = saveAuthVo.getBirth();
        this.mobile = saveAuthVo.getMobile();
        this.telecom = saveAuthVo.getTelecom();
        this.gender = saveAuthVo.getGender();
        this.updateDt = LocalDateTime.now();

        this.createDt = LocalDateTime.now();
    }

}
