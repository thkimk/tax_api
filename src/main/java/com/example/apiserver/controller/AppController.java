package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.Utils;
import com.example.apiserver.advice.exception.InvalidInputValueException;
import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.dto.AppInitsDto;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.service.AppService;
import com.example.apiserver.service.ResponseService;
import com.example.apiserver.vo.NomemberVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

        AppInitsDto appInitsDto = appService.inits();
        return responseService.result(appInitsDto);
    }


    @ApiOperation(value = "App 기동시 비회원 메타정보", notes = "App 기동시, App에서 비회원 메타정보를 서버로 전달한다.")
    @PostMapping(value = "/nomember")
    public ApiDataResult nomember(@RequestBody NomemberVo nomemberVo) throws Exception {
        Utils.logCalled("nomember", nomemberVo);
        appService.nomember(nomemberVo);

        return responseService.successResult();
    }

}
