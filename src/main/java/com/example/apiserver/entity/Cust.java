package com.example.apiserver.entity;

import com.example.apiserver.Constants;
import com.example.apiserver.Utils;
import com.example.apiserver.vo.SaveAuthVo;
import com.example.apiserver.vo.SignupVo;
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
@Table(name = "cust") // 'user' 테이블과 매핑됨을 명시한다.
public class Cust {

    @Id
    @Column(name="cust_id", length = 9)
    private String custId;

    @Column(name="cust_status", length = 2)
    private String custStatus;

    @Column(name="cust_grade", length = 2)
    private String custGrade;

    @Column(name="asct_in_dt")
    private LocalDateTime asctInDt;

    @Column(name="asct_out_dt")
    private LocalDateTime asctOutDt;

    @Column(name="reg_in_dt")
    private LocalDateTime regInDt;

    @Column(name="reg_out_dt")
    private LocalDateTime regOutDt;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;

    // 회원가입시 생성
    public Cust(SignupVo signupVo) {
        this.custId = Utils.genCustId();
        this.custStatus = Constants.CUST_ST_NORMAL;
        this.custGrade = Constants.CUST_GR_ASOC;
        this.createDt = LocalDateTime.now();

    }

}
