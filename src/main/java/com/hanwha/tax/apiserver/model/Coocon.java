package com.hanwha.tax.apiserver.model;

import com.hanwha.tax.apiserver.util.Utils;
import kcb.org.json.JSONObject;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

@Component
@Data
@Slf4j
public class Coocon {
    @Autowired
    RestTemplate restTemplate;

    @Value("${tax.coocon.client_id}")
    private String clientId = "";

    @Value("${tax.coocon.secret}")
    private String clientSecret = "";

    @Value("${tax.coocon.url}")
    private String cooconUrl = "";


    public static String COOCON_AUTH = "";


    public void callCooconApiToken() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(new MediaType("application", "x-www-form-urlencoded", Charset.forName("UTF-8")));

        MultiValueMap<String, String> cooconTokenParams = new LinkedMultiValueMap<>();
        {
            cooconTokenParams.add("grant_type", "client_credentials");
            cooconTokenParams.add("scope", "apis");
            cooconTokenParams.add("client_id", clientId);
            cooconTokenParams.add("client_secret", clientSecret);
        }

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity(cooconTokenParams, httpHeaders);

        ResponseEntity<String> responseEntity = restTemplate.exchange(cooconUrl.concat("/oauth/2.0/token"), HttpMethod.POST, entity, String.class);
        log.debug("## callCooconApiToken(): responseBody: ", responseEntity.getBody());

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        String tokenTmp = (String)jsonObject.get("access_token");
        if (tokenTmp != null) COOCON_AUTH = tokenTmp;

    }

    public <T> T callCooconApi(String url, Object obj, Class<T> resType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String transId = "hanwha_".concat(Utils.random10value());

        httpHeaders.add("x-api-tran-id", transId);
        httpHeaders.add("Authorization", "Bearer ".concat(COOCON_AUTH));

        HttpEntity entity = new HttpEntity(obj, httpHeaders);
        Object res = restTemplate.postForObject(cooconUrl.concat(url), entity, resType);
        Utils.logExtCallReturned("callCooconApi", res);

        return (T)res;
    }

}
