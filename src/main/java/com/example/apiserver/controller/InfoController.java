package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.service.ResponseService;
import com.example.apiserver.vo.JobsVo;
import com.example.apiserver.vo.SignupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Info 정보"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.API + "/" + Constants.VERSION + "/info")
public class InfoController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ


    @ApiOperation(value = "약관 정보", notes = "약관동의에 필요한 약관정보를 제공한다.")
    @PostMapping(value = "/terms")
    public ApiDataResult terms() {
        return responseService.result(new String("terms result"));
    }

    @ApiOperation(value = "업종리스트", notes = "업종리스트를 제공한다. 전체를 제공하거나, 검색을 통한 일부를 제공할 수 있다.")
    @PostMapping(value = "/jobs")
    public ApiDataResult jobs(@ApiParam(value = "회원ID : 이메일", required = false) @RequestBody JobsVo jobsVo) {
        if (jobsVo == null) {
            return responseService.result(new String("jobs ALL result"));
        }
        return responseService.result(new String("jobs result"));
    }


}
