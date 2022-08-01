package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SaveFamilyVo;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "cust_family")
public class CustFamily extends TimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long familySeq;

    @Column(name = "cust_id", length = 10)
    private String cid;

    @Column(name = "family", length = 2)
    private String family;

    @Column(name = "birth")
    private String birth;

    @Column(name = "is_disorder")
    private Character isDisorder;


    public CustFamily(String custId, SaveFamilyVo.Family family) {
        this.cid = custId;
        this.family = family.getFamily();
        this.birth = family.getBirth();
        this.isDisorder = family.getIsDisorder();
    }

    public CustFamily(SimTaxVo.DetailsVo.FamilyVo familyVo) {
        this.family = familyVo.getFamily();
        this.birth = familyVo.getBirth();
        this.isDisorder = familyVo.getIsDisorder();
    }

}
