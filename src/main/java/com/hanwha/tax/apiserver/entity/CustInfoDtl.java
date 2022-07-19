package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SaveJobVo;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Data //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "cust_info_dtl") // 'user' 테이블과 매핑됨을 명시한다.
public class CustInfoDtl {

    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Column(name="indst_code", length = 6)
    private String jobCode;

    @Column(name="is_disorder")
    private Character isDisorder;

    @Column(name="is_hshld")
    private Character isHshld;

    @Column(name="is_new_busin")
    private Character isNewBusin;

    @Column(name="is_marriage")
    private Character isMarriage;

    @Column(name="is_sin_parent")
    private Character isSinParent;

    @Column()
    private String taxFlag = "00";

    public Character getIsDisorder() {
        return isDisorder == null? Character.valueOf(' ') : isDisorder;
    }


    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    public CustInfoDtl(SignupVo signupVo) {
        this.cid = signupVo.getCid();

        this.jobCode = signupVo.getJobCode();

        this.isDisorder = signupVo.getIsDisorder().toCharacter();
        this.isHshld = signupVo.getIsHshld().toCharacter();
        this.isNewBusin = signupVo.getIsNewBusin().toCharacter();
        this.isMarriage = signupVo.getIsMarriage().toCharacter();
        this.isSinParent = signupVo.getIsSinParent().toCharacter();

/*
        this.pushToken = signupVo.getPushToken();


        String[] devs = Utils.devs();
        this.devUid = MDC.get("uid");
        if (devs != null && devs.length == 4) {
            this.devName = devs[1];
            this.devOstype = devs[2];
            this.devOsversion = devs[3];
        }
*/

        this.createDt = LocalDateTime.now();

    }

    public void fill(SaveJobVo saveJobVo) {

        jobCode = saveJobVo.getJobCode();
    }

}
