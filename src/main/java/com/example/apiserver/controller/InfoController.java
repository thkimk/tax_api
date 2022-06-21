package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.Utils;
import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.dto.JobsDto;
import com.example.apiserver.dto.TermsDto;
import com.example.apiserver.entity.Industry;
import com.example.apiserver.entity.Terms;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.repository.TermsRepository;
import com.example.apiserver.service.InfoService;
import com.example.apiserver.service.ResponseService;
import com.example.apiserver.vo.JobsVo;
import com.example.apiserver.vo.SignupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Info 정보"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.API + "/" + Constants.VERSION + "/info")
public class InfoController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    InfoService infoService;


    @ApiOperation(value = "약관 정보", notes = "약관동의에 필요한 약관정보를 제공한다.")
    @GetMapping(value = "/terms")
    public ApiDataResult terms() {
        Utils.logCalled("terms", null);

        List<Terms> terms = infoService.terms();
        return responseService.result(terms);
    }

    @ApiOperation(value = "업종리스트", notes = "업종리스트를 제공한다. 전체를 제공하거나, 검색을 통한 일부를 제공할 수 있다.")
    @GetMapping(value = "/jobs")
    public ApiDataResult jobs(@ApiParam(value = "업종 코드", required = false) @RequestParam(required = false) String code,
                              @ApiParam(value = "업종명", required = false) @RequestParam(required = false) String name) throws Exception {
        Utils.logCalled("jobs", code+", "+name);
        List<Industry> industries = infoService.jobs(code, name);
        return responseService.result(industries);
    }


}
