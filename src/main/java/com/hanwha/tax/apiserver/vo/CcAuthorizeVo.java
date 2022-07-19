package com.hanwha.tax.apiserver.vo;


import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.entity.CustInfo;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CcAuthorizeVo {
    String ci;
    String user_name;
    String birth_date;
    String tele_corp;
    String ph_no;
    String gender;
    String national_yn;
    String email;

    public CcAuthorizeVo(String ci, CustInfo custInfo) {
        this.ci = ci;
        this.user_name = Utils.encCoocon(custInfo.getName());
        this.birth_date = Utils.encCoocon(custInfo.getBirth());
        this.tele_corp = "02";
        this.ph_no = Utils.encCoocon(custInfo.getMobile());
        this.gender = String.valueOf(custInfo.getGender());
        this.national_yn = "Y";
        this.email = Utils.encCoocon(custInfo.getEmail());

    }
}

