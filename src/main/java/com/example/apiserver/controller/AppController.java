package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.advice.exception.InvalidInputValueException;
import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.dto.AppInitsDto;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.service.AppService;
import com.example.apiserver.service.ResponseService;
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
    public ApiDataResult appInits() throws Exception {
        if (true) throw new InvalidInputValueException();
        log.info("## AppController: appInits() starts..");
        AppInitsDto appInitsDto = appService.appInits();
        return responseService.result(appInitsDto);
    }

}
