package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.dto.CooconExpenseDto;
import com.hanwha.tax.apiserver.dto.CooconIncome;
import com.hanwha.tax.apiserver.dto.IncomeDto;
import com.hanwha.tax.apiserver.vo.ExpenseVo;
import com.hanwha.tax.apiserver.vo.IncomeVo;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MydataService {
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
        log.info("## callCooconApiToken(): responseBody: ", responseEntity.getBody());

        JSONObject jsonObject = new JSONObject(responseEntity.getBody());
        String tokenTmp = (String)jsonObject.get("access_token");
        if (tokenTmp != null) COOCON_AUTH = tokenTmp;

    }

    public Object callCooconApi(String url, Object obj) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String transId = "tax_".concat(UUID.randomUUID().toString());

        httpHeaders.add("x-api-tran-id", transId);
        httpHeaders.add("Authorization", "Bearer ".concat(COOCON_AUTH));

        HttpEntity entity = new HttpEntity(obj, httpHeaders);
        ResponseEntity<String> responseEntity = restTemplate.exchange(cooconUrl.concat(url), HttpMethod.POST, entity, String.class);

        return responseEntity.getBody();

    }


    public IncomeDto mydataIncome(IncomeVo incomeVo) {
        callCooconApiToken();
        IncomeDto incomeDto = new IncomeDto();

        // 쿠콘 API 호출
        CooconIncome cooconIncome = new CooconIncome(incomeVo.getCi());
        Object res = callCooconApi("/apis/user/hw/bank/income", cooconIncome);

        return incomeDto;
    }


    public IncomeDto mydataExpense(ExpenseVo expenseVo) {
        IncomeDto incomeDto = new IncomeDto();

        // 쿠콘 API 호출
        CooconExpenseDto cooconExpenseDto = new CooconExpenseDto(expenseVo.getCi());
        Object res = callCooconApi("/apis/user/hw/card/expense", cooconExpenseDto);

        return incomeDto;
    }

}

