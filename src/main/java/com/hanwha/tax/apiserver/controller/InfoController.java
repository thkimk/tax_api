package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.entity.Industry;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.InfoService;
import com.hanwha.tax.apiserver.service.ResponseService;
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

        return responseService.result(infoService.terms().getData());
    }

    @ApiOperation(value = "업종리스트", notes = "업종리스트를 제공한다. 전체를 제공하거나, 검색을 통한 일부를 제공할 수 있다.")
    @GetMapping(value = "/jobs")
    public ApiDataResult jobs(@ApiParam(value = "업종 코드", required = false) @RequestParam(required = false) String code,
                              @ApiParam(value = "업종명", required = false) @RequestParam(required = false) String name) throws Exception {
        Utils.logCalled("jobs", code + ", " + name);
        final List<Industry> industries = infoService.jobs(code, name);

        return responseService.result(industries);
    }


}
