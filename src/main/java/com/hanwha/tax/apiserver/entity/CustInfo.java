package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.SaveAuthVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.Getter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "cust_info")
public class CustInfo extends TimeEntity{

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

    private String telecom;

    @Column()
    private Character gender;


    // 회원가입시 생성
    public CustInfo(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        this.name = signupVo.getName();
        this.email = signupVo.getEmail();
        this.birth = signupVo.getBirth();
        this.mobile = signupVo.getMobile();
        this.telecom = signupVo.getTelecom().getCode();
        this.gender = signupVo.getGender().toCharacter();

    }

    public CustInfo(SaveAuthVo saveAuthVo) {
        this.cid = Utils.cid();
        this.name = saveAuthVo.getName();
        this.email = saveAuthVo.getEmail();
        this.birth = saveAuthVo.getBirth();
        this.mobile = saveAuthVo.getMobile();
        this.telecom = saveAuthVo.getTelecom();
        this.gender = saveAuthVo.getGender();
    }

}
