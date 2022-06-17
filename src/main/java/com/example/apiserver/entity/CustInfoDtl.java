package com.example.apiserver.entity;

import com.example.apiserver.vo.SaveJobVo;
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
    @Column(name="cust_id", length = 9)
    private String custId;

    @Column(name="indst_code", length = 6)
    private String indstCode;;

    @Column(name="is_disorder")
    private char isDisorder;

    @Column(name="is_hshld")
    private char isHshld;

    @Column(name="is_ag_mydata")
    private char isAgMydata;

    @Column(name="is_ag_mkrecv")
    private char isAgMkrecv;

    @Column(name="is_new_bsns")
    private char isNewBsns;

    @Column(name="dev_id", length = 36)
    private String devId;;

    @Column(name="create_dt")
    private LocalDateTime createDt;

    @Column(name="update_dt")
    private LocalDateTime updateDt;


    public CustInfoDtl(SaveJobVo saveJobVo) {
    }

    public void fill(SaveJobVo saveJobVo) {
        indstCode = saveJobVo.getJobCode();
    }

}
