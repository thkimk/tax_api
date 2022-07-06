package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.dto.AuthorizeDto;
import com.hanwha.tax.apiserver.dto.CCAuthorizeDto;
import com.hanwha.tax.apiserver.repository.AuthInfoRepository;
import com.hanwha.tax.apiserver.vo.CCAuthorizeVo;
import com.hanwha.tax.apiserver.vo.IncomeVo;
import com.hanwha.tax.apiserver.dto.ExpenseDto;
import com.hanwha.tax.apiserver.dto.IncomeDto;
import com.hanwha.tax.apiserver.vo.ExpenseVo;
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

    @Autowired
    AuthInfoRepository authInfoRepository;


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

    public <T> T callCooconApi(String url, Object obj, Class<T> resType) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String transId = "hanwha_".concat(Utils.random10value());

        httpHeaders.add("x-api-tran-id", transId);
        httpHeaders.add("Authorization", "Bearer ".concat(COOCON_AUTH));

        HttpEntity entity = new HttpEntity(obj, httpHeaders);
        Object res = restTemplate.postForObject(cooconUrl.concat(url), entity, resType);

        return (T)res;
    }


    public IncomeDto mydataIncome() {
        // 쿠콘 API 호출
        String ci = authInfoRepository.getCiByCustId(Utils.custId());
        IncomeVo incomeVo = new IncomeVo(ci);

        IncomeDto incomeDto = (IncomeDto)callCooconApi("/apis/user/hw/bank/income", incomeVo, IncomeDto.class);

        return incomeDto;
    }

/*
    public IncomeDto mydataIncome2(IncomeVo incomeVo) {
        // 쿠콘 API 호출
        CooconIncome cooconIncome = new CooconIncome(incomeVo.getCi());

//        IncomeDto incomeDto = (IncomeDto)callCooconApi("/apis/user/hw/bank/income", cooconIncome);
        IncomeDto incomeDto = null;
        {
            String url = "/apis/user/hw/bank/income";
            Object obj = cooconIncome;

            HttpHeaders httpHeaders = new HttpHeaders();
            String transId = "hanwha_".concat(Utils.random10value());

            httpHeaders.add("x-api-tran-id", transId);
            httpHeaders.add("Authorization", "Bearer ".concat(COOCON_AUTH));

            HttpEntity entity = new HttpEntity(obj, httpHeaders);
            incomeDto = restTemplate.postForObject(cooconUrl.concat(url), entity, IncomeDto.class);
        }

        return incomeDto;
    }
*/


    public ExpenseDto mydataExpense() {
        String ci = authInfoRepository.getCiByCustId(Utils.custId());
        ExpenseVo expenseVo = new ExpenseVo(ci);

        // 쿠콘 API 호출
        ExpenseDto expenseDto = callCooconApi("/apis/user/hw/card/expense", expenseVo, ExpenseDto.class);

        return expenseDto;
    }


    public CCAuthorizeDto authorize() {
        String ci = authInfoRepository.getCiByCustId(Utils.custId());
        CCAuthorizeVo ccAuthorizeVo = new CCAuthorizeVo(ci);

        // 쿠콘 API 호출
        CCAuthorizeDto ccAuthorizeDto = callCooconApi("/apis/user/authorize", ccAuthorizeVo, CCAuthorizeDto.class);

        return ccAuthorizeDto;
    }

}

