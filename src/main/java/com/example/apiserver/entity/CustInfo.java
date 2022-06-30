package com.example.apiserver.entity;

import com.example.apiserver.Constants;
import com.example.apiserver.vo.SaveAuthVo;
import com.example.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.slf4j.MDC;

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
    private String custId;

    @Column(length = 50)
    private String name;

    @Column(length = 256)
    private String email;

    @Column(length = 8)
    private String birth;

    @Column(length = 15)
    private String mobile;

    @Column()
    private Character gender;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    // 회원가입시 생성
    public CustInfo(SignupVo signupVo, String custId) {
        this.custId = custId;
        this.name = signupVo.getName();
        this.email = signupVo.getEmail();
        this.birth = signupVo.getBirth();
        this.mobile = signupVo.getMobile();
        this.gender = signupVo.getGender();
        this.createDt = LocalDateTime.now();

    }

    public CustInfo(SaveAuthVo saveAuthVo) {
        this.custId = MDC.get("custId");
        this.name = saveAuthVo.getName();
        this.email = saveAuthVo.getEmail();
        this.birth = saveAuthVo.getBirth();
        this.mobile = saveAuthVo.getMobile();
        this.gender = saveAuthVo.getGender();
        this.updateDt = LocalDateTime.now();

        this.createDt = LocalDateTime.now();
    }

}
