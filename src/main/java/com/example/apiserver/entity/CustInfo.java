package com.example.apiserver.entity;

import com.example.apiserver.Constants;
import com.example.apiserver.vo.SignupVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
@Table(name = "cust_info") // 'user' 테이블과 매핑됨을 명시한다.
public class CustInfo {

    @Id
    @Column(name="cust_id", length = 9)
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
    private char gender;

    @Column()
    private char isMarriage;

    @Column()
    private LocalDateTime create_dt;

    @Column()
    private LocalDateTime update_dt;


    // 회원가입시 생성
    CustInfo(SignupVo signupVo, String custId) {
        this.custId = custId;
        this.name = signupVo.getName();
        this.email = signupVo.getEmail();
        this.birth = signupVo.getBirth();
        this.mobile = signupVo.getMobile();
        this.gender = signupVo.getGender();
        this.isMarriage = signupVo.getIsMarriage();

    }
}
