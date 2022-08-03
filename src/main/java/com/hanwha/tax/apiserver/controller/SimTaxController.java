package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.model.SimTax;
import com.hanwha.tax.apiserver.model.Tax;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.service.TaxService;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.SimTaxVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"소득세 정보 시뮬레이션"})
@RequiredArgsConstructor
@Controller
@RequestMapping(value = Constants.PRE_ADDRESS + "simtax")
public class SimTaxController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    TaxService taxService;

    private final Tax tax;


    /**
     * 예상 소득세 시뮬레이션
     * preIncome을 0으로 잡으면, 신규사업자로 구분
     * outgoing을 0으로 잡으면, 경비율로 계산
     * @param simTaxVo (업종, 직전년도, 당해년도, 지출 만 입력)
     * @return
     */
    @ApiOperation(value = "예상 소득세 시뮬레이션", notes = "고객의 특정년도 예상소득세를 시뮬레이션한다.")
    @GetMapping(value = "/simTax")
    public String simTax(Model model, SimTaxVo simTaxVo) {
        Utils.logCalled("simtax/simTax", simTaxVo);

        taxService.simTax(simTaxVo);

        SimTax simTax = new SimTax(tax);
        model.addAttribute("simtax", simTax);

        return "simTaxView";
    }


    @ApiOperation(value = "예상 소득세 시뮬레이션", notes = "고객의 특정년도 예상소득세를 시뮬레이션한다.")
    @PostMapping(value = "/simTaxDetails")
    public String simTaxDetails(Model model, @RequestBody SimTaxVo simTaxVo) {
        Utils.logCalled("simtax/simTaxDetails", simTaxVo);

        taxService.simTax(simTaxVo);

        SimTax simTax = new SimTax(tax);
        model.addAttribute("simtax", simTax);

        return "simTaxView";
    }

}

