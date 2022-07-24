package com.hanwha.tax.apiserver.model;

import kcb.module.v3.exception.OkCertException;
import kcb.org.json.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
@Slf4j
public class Kcb {
    @Value("${tax.kcbMemberKey}")
    private String KCB_MEMBER_KEY = "";

    @Value("${tax.licence}")
    private String KCB_LICENCE = "";


    public JSONObject callKcb(String svcName, String param) {
        try {
            kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
            String resultStr = okcert.callOkCert("PROD", KCB_MEMBER_KEY, svcName, KCB_LICENCE, param);

            if (resultStr != null) {
                log.info("## resJson : {}", resultStr);
                return new JSONObject(resultStr);
            }
        } catch (OkCertException e) {
        }

        return null;
    }

}
