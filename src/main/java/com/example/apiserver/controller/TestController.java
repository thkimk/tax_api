package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.model.Info;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//@Api(tags = {"0. Test"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/test")
public class TestController {

    private final ResponseService responseService;

    @ApiOperation(value = "header test", notes = "header test mock")
    @GetMapping(value = "/header")
    public ApiDataResult header(@RequestHeader Map<String, String> headers) {
        return responseService.successResult("headers : " + headers.get("test"));
    }

    @ApiOperation(value = "fail test", notes = "fail test mock")
    @GetMapping(value = "/fail")
    public ApiDataResult fail() {

        return responseService.failResult();
    }

    @ApiOperation(value = "single mock test", notes = "test single mock")
    @GetMapping(value = "/mock/single")
    public ApiDataResult singMock() {
        Info info = new Info();
        info.setVersion("1.0.0");
        info.setType(0);
        info.setUserId("1231231313");
        return responseService.result(info);
    }

    @ApiOperation(value = "list mock test", notes = "test list mock")
    @GetMapping(value = "/mock/list")
    public ApiDataResult listMock() {
        List<Info> infoList = new ArrayList<>();
        for (int i = 0; i < 4; i++) {
            Info info = new Info();
            info.setVersion("1.0.0");
            info.setType(0);
            info.setUserId("1231231313"+i);
            infoList.add(info);
        }
        return responseService.result(infoList);
    }

}