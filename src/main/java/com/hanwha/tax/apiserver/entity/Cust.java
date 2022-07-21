package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.vo.SignupRegVo;
import com.hanwha.tax.apiserver.vo.SignupVo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cust")
public class Cust extends TimeEntity {

    @Id
    @Column(name = "cust_id", length = 10)
    private String cid;

    @Column(name = "cust_status", length = 2)
    private String custStatus;

    @Column(name = "cust_grade", length = 2)
    private String custGrade;

    @Column(name = "asct_in_dt")
    private LocalDateTime asctInDt;

    @Column(name = "asct_out_dt")
    private LocalDateTime asctOutDt;

    @Column(name = "reg_in_dt")
    private LocalDateTime regInDt;

    @Column(name = "reg_out_dt")
    private LocalDateTime regOutDt;

    // 회원가입시 생성
    public Cust(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        this.custStatus = Constants.CUST_ST_NORMAL;
        this.custGrade = Constants.CUST_GR_ASOC;
    }

    public Cust(SignupRegVo signupVo) {
//        this.custId = custId;
        this.custStatus = Constants.CUST_ST_NORMAL;
        this.custGrade = Constants.CUST_GR_ASOC;
    }

}
