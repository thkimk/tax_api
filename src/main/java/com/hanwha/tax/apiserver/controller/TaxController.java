package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.service.TaxService;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"소득세 정보"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.PRE_ADDRESS + "tax")
public class TaxController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    TaxService taxService;


    @ApiOperation(value = "예상 소득세 요청", notes = "고객의 특정년도 예상소득세를 조회한다.")
    @GetMapping(value = "/predTax")
    public ApiDataResult predTax(@RequestParam(required = false) Integer year) {
        Utils.logCalled("tax/predTax", year);

        Long tax = taxService.predTax(year);

        return responseService.result(tax);
    }


}
