package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.dto.AppInitsDto;
import com.hanwha.tax.apiserver.dto.MainMenuDto;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.AppService;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.vo.NomemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"App 앱"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.API + "/" + Constants.VERSION + "/app")
@Slf4j
public class AppController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code

    @Autowired
    AppService appService;


    @ApiOperation(value = "App 기동시 셋업데이터", notes = "App 기동시 필요한 데이터를 제공한다.")
    @GetMapping(value = "/inits")
    public ApiDataResult inits() throws Exception {
        Utils.logCalled("inits", "");

        final AppInitsDto appInitsDto = appService.inits();
        return responseService.result(appInitsDto);
    }


    @ApiOperation(value = "App 기동시 비회원 메타정보", notes = "App 기동시, App에서 비회원 메타정보를 서버로 전달한다.")
    @PostMapping(value = "/nomember")
    public ApiDataResult nomember(@RequestBody NomemberVo nomemberVo) throws Exception {
        Utils.logCalled("nomember", nomemberVo);
        appService.nomember(nomemberVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "메인메뉴 정보", notes = "App 기동시, 고객 등급에 따른 메인메뉴 정보를 제공한다.")
    @GetMapping(value = "/mainMenu")
    public ApiDataResult mainMenu() throws Exception {
        Utils.logCalled("mainMenu", "");
        final MainMenuDto mainMenuDto = appService.mainMenu();

        return responseService.result(mainMenuDto);
    }

}
