package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SaveFamilyVo;

import javax.persistence.*;

@Entity
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

}
