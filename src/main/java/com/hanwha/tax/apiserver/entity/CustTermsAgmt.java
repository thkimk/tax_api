package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Builder // builder를 사용할 수 있게 한다.
@Entity // jpa entity 임을 알린다.
@Data //user 필드 값의 getter를 자동생성한다.
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
@AllArgsConstructor // 인자를 모두 갖춘 생성자를 자동으로 생성한다.
@Table(name = "cust_terms_agmt") // 'user' 테이블과 매핑됨을 명시한다.
public class CustTermsAgmt {

    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Column()
    private Long termsId;

    @Column()
    private Character isAgree;

    @Column()
    private LocalDateTime agmtDt;

    public CustTermsAgmt(SignupVo signupVo) {
        this.cid = signupVo.getCid();
        this.isAgree = 'Y';
        this.agmtDt = LocalDateTime.now();

    }

    public static List<CustTermsAgmt> custTermsAgmts(SignupVo signupVo) {
        List<CustTermsAgmt> custTermsAgmts = null;
        String[] termsAgmts = signupVo.getAgreeTerms().split(";")[0].split(",");

        if (termsAgmts.length > 0) {
            custTermsAgmts = new ArrayList<>();
            for (String termsAgmt : termsAgmts) {
                CustTermsAgmt custTermsAgmt = new CustTermsAgmt();
                custTermsAgmt.setCid(signupVo.getCid());
                custTermsAgmt.setIsAgree('Y');
                custTermsAgmt.setAgmtDt(LocalDateTime.now());
                custTermsAgmt.setTermsId(Long.valueOf(termsAgmt));

                custTermsAgmts.add(custTermsAgmt);
            }

        }

        return custTermsAgmts;
    }

}
