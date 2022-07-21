package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.entity.ids.CustDeductIds;
import com.hanwha.tax.apiserver.entity.ids.CustTermsAgmtIds;
import com.hanwha.tax.apiserver.vo.SignupVo;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Entity
@Data
@Table(name = "cust_terms_agmt")
@IdClass(CustTermsAgmtIds.class)
@NoArgsConstructor // 인자 없는 생성자를 자동으로 생성한다.
public class CustTermsAgmt {

    @Id
    @Column(name="cust_id", length = 10)
    private String cid;

    @Id
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
