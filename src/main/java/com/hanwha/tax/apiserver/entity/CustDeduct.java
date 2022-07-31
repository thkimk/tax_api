package com.hanwha.tax.apiserver.entity;

import com.hanwha.tax.apiserver.entity.ids.CustDeductIds;
import com.hanwha.tax.apiserver.vo.DeductVo;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cust_deduct")
@IdClass(CustDeductIds.class)
public class CustDeduct extends TimeEntity {

    @Id
    @Column(name = "cust_id", length = 10, columnDefinition = "varchar(10) comment '회원번호'")
    private String cid;

    @Id
    @Column(name = "year", columnDefinition = "int comment '연도'")
    private int year;

    @ColumnDefault("0")
    @Column(name = "income", nullable = false, columnDefinition = "bigint  default 0 comment '수입'")
    private Long income = 0L;

    @ColumnDefault("0")
    @Column(name = "npc_amt", nullable = false, columnDefinition = "bigint default 0 comment '국민연금보험료 납입액'")
    private Long npcAmount = 0L;

    @ColumnDefault("0")
    @Column(name = "rsp_amt", nullable = false, columnDefinition = "bigint default 0 comment '연금저축 보유 납입액'")
    private Long rspAmount = 0L;

    @ColumnDefault("0")
    @Column(name = "ira_amt", nullable = false, columnDefinition = "bigint default 0 comment '연금저축계좌보유 납입액'")
    private Long iraAmount = 0L;

    @ColumnDefault("0")
    @Column(name = "med_amt", nullable = false, columnDefinition = "bigint default 0 comment '소상공인 공제부금 보유 납입액'")
    private Long medAmount = 0L;

    @ColumnDefault("0")
    @Column(name = "sed_amt", nullable = false, columnDefinition = "bigint default 0 comment '중소기업창업투자 금액'")
    private Long sedAmount = 0L;


    public CustDeduct(DeductVo deductVo) {
        cid = deductVo.getCid();
        year = deductVo.getYear();

        npcAmount = deductVo.getNpcAmount();
        rspAmount = deductVo.getRspAmount();
        iraAmount = deductVo.getIraAmount();
        medAmount = deductVo.getMedAmount();
        sedAmount = deductVo.getSedAmount();

    }

    public CustDeduct(SimTaxVo simTaxVo) {

    }

}
