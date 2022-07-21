package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cust_terms_agmt")
public class CustTermsAgmt extends TimeEntity {

    @Id
    @Column(name = "cust_id", length = 10)
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
