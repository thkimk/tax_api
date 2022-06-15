package com.example.apiserver.dto;

import kcb.org.json.JSONObject;
import lombok.Data;

@Data
public class IdenOtpReqDto {
    String rsltCd;
    String rsltMsg;
    String txSeqNo;


    public int fill(JSONObject jsonObject) {
        rsltCd = jsonObject.getString("RSLT_CD");
        rsltMsg = jsonObject.getString("RSLT_MSG");
        if (!jsonObject.isNull("TX_SEQ_NO")) txSeqNo = jsonObject.getString("TX_SEQ_NO");
//        if (!jsonObject.isNull("CI")) ci = jsonObject.getString("CI");

        return 0;
    }

}

