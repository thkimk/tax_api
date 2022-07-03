package com.hanwha.tax.apiserver.controller;


import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.dto.IncomeDto;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.MydataService;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.vo.ExpenseVo;
import com.hanwha.tax.apiserver.vo.IncomeVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Mydata 정보"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.API + "/" + Constants.VERSION + "/mydata")
public class MydataController {
    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    MydataService mydataService;


    @ApiOperation(value = "Income 요청", notes = "마이데이터(수입정보)를 실시간 조회한다.")
    @PostMapping(value = "/income")
    public ApiDataResult income(@RequestBody IncomeVo incomeVo) {
        Utils.logCalled("income", incomeVo);

        IncomeDto incomeDto = mydataService.mydataIncome(incomeVo);

        return responseService.result(incomeDto);
    }


    @ApiOperation(value = "Exponse 요청", notes = "마이데이터(지출정보)를 실시간 조회한다.")
    @PostMapping(value = "/expense")
    public ApiDataResult expense(@RequestBody ExpenseVo expenseVo) {
        Utils.logCalled("expense", expenseVo);

        IncomeDto incomeDto = mydataService.mydataExpense(expenseVo);

        return responseService.result(incomeDto);
    }


}
