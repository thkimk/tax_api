package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.dto.FaqDto;
import com.hanwha.tax.apiserver.dto.TermsDetailDto;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.InfoService;
import com.hanwha.tax.apiserver.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Info 정보"})
@CrossOrigin
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
    public ApiDataResult getTermsList() {
        Utils.logCalled("terms", null);

        return responseService.result(infoService.getTermsList());
    }

    @ApiOperation(value = "약관 상세 정보", notes = "약관 상세정보를 제공한다.")
    @GetMapping(value = "/terms/{id}")
    public ApiDataResult getTermsDetail(@PathVariable Long id) {
        Utils.logCalled("terms", null);
        final TermsDetailDto termsDetail = infoService.getTerms(id);

        if (termsDetail!= null) {
            return responseService.result(termsDetail);
        } else {
            return responseService.failResult(400, "해당 약관이 없습니다.");
        }
    }

    @ApiOperation(value = "업종리스트", notes = "업종리스트를 제공한다. 전체를 제공하거나, 검색을 통한 일부를 제공할 수 있다.")
    @GetMapping(value = "/jobs")
    public ApiDataResult jobs(@ApiParam(value = "업종 코드") @RequestParam(required = false) String code,
                              @ApiParam(value = "업종명") @RequestParam(required = false) String name) throws Exception {
        Utils.logCalled("jobs", code + ", " + name);

        return responseService.result(infoService.jobs(code, name));
    }


    @ApiOperation(value = "자주 묻는 질문 리스트", notes = "자주 묻는 질문 목록을 제공한다.")
    @GetMapping(value = "/faq")
    public ApiDataResult getFaqList() {
        Utils.logCalled("faq", null);

        return responseService.result(infoService.getFaqList());
    }

    @ApiOperation(value = "자주 묻는 질문 상세", notes = "자주 묻는 질문 상세내용을 제공한다.")
    @GetMapping(value = "/faq/{id}")
    public ApiDataResult getFaq(@PathVariable Long id) {
        Utils.logCalled("faq", null);

        final FaqDto faqDto = infoService.getFaq(id);

        if (faqDto!= null) {
            return responseService.result(faqDto);
        } else {
            return responseService.failResult(400, "해당 질문이 없습니다.");
        }
    }
}
