package com.hanwha.tax.apiserver.model;


import com.hanwha.tax.apiserver.entity.CustInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class Masking {
    String mName;
    String mEmail;
    String mMobile;


    CustInfo custInfo = null;

    public void init(CustInfo custInfo) {
        this.custInfo = custInfo;

    }

    public String maskName(String name) {
        String res = "";
        return res;
    }

    public String maskEmail(String email) {
        String res = "";
        return res;
    }

    public String maskMobile(String mobile) {
        String res = "";
        return res;
    }



}
