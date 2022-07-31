package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.vo.SaveJobVo;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "cust_info_dtl")
public class CustInfoDtl extends TimeEntity {

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

    @Column(name="tax_flag")
    private String taxFlag = "00";

    public Character getIsDisorder() {
        return isDisorder == null? Character.valueOf(' ') : isDisorder;
    }


    public CustInfoDtl(SaveJobVo saveJobVo) {
        this.cid = saveJobVo.getCid();
        this.isNewBusin = saveJobVo.getIsNewBusin().toCharacter();
        this.jobCode = saveJobVo.getJobCode();
        this.isDisorder = 'N';
        this.isHshld = 'N';
        this.isMarriage = 'N';
        this.isSinParent = 'N';
    }

    public void fill(SaveJobVo saveJobVo) {
        if (saveJobVo.getIsNewBusin() != null) {
            this.isNewBusin = saveJobVo.getIsNewBusin().toCharacter();
        }
        this.jobCode = saveJobVo.getJobCode();
    }

    public CustInfoDtl(SimTaxVo simTaxVo) {

    }


}
