package com.hanwha.tax.apiserver.controller;


import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.entity.BookIncome;
import com.hanwha.tax.apiserver.entity.BookOutgoing;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.dto.CcAuthorizeDto;
import com.hanwha.tax.apiserver.dto.CcExpenseDto;
import com.hanwha.tax.apiserver.dto.CcIncomeDto;
import com.hanwha.tax.apiserver.entity.TotalIncome;
import com.hanwha.tax.apiserver.entity.TotalOutgoing;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.MydataService;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.vo.BookIncomeVo;
import com.hanwha.tax.apiserver.vo.BookOutgoingVo;
import com.hanwha.tax.apiserver.vo.DeductVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"Mydata 정보"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.PRE_ADDRESS + "mydata")
public class MydataController {
    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    MydataService mydataService;


    @ApiOperation(value = "Income 요청", notes = "마이데이터(수입정보)를 실시간 조회한다.")
    @GetMapping(value = "/ccIncome")
    public ApiDataResult ccIncome() {
        Utils.logCalled("ccIncome", "");

        CcIncomeDto ccIncomeDto = mydataService.ccIncome();

        return responseService.result(ccIncomeDto);
    }


    @ApiOperation(value = "Exponse 요청", notes = "마이데이터(지출정보)를 실시간 조회한다.")
    @GetMapping(value = "/ccExpense")
    public ApiDataResult ccExpense() {
        Utils.logCalled("ccExpense", "");

        CcExpenseDto ccExpenseDto = mydataService.ccExpense();

        return responseService.result(ccExpenseDto);
    }


    @ApiOperation(value = "쿠콘 인증번호 요청", notes = "쿠콘의 인증번호를 요청한다.")
    @GetMapping(value = "/ccAuthorize")
    public ApiDataResult ccAuthorize() {
        Utils.logCalled("ccAuthorize", "");

        CcAuthorizeDto ccAuthorizeDto = mydataService.ccAuthorize();

        return responseService.result(ccAuthorizeDto);
    }

    @ApiOperation(value = "전체 수입 조회", notes = "전체 수입을 월별로 조회한다.")
    @GetMapping(value = "/totalIncome")
    public ApiDataResult totalIncome(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Utils.logCalled("totalIncome", year+", "+ month);

        List<TotalIncome> totalIncomes = mydataService.totalIncome(year, month);

        return responseService.result(totalIncomes);
    }

    @ApiOperation(value = "전체 지출 조회", notes = "전체 지출을 월별로 조회한다.")
    @GetMapping(value = "/totalOutgoing")
    public ApiDataResult totalOutgoing(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Utils.logCalled("totalOutgoing", year+", "+ month);

        List<TotalOutgoing> totalOutgoings = mydataService.totalOutgoing(year, month);

        return responseService.result(totalOutgoings);
    }


    @ApiOperation(value = "간편장부 수입 조회", notes = "간편장부에 입력한 수입을 월별로 조회한다.")
    @GetMapping(value = "/bookIncome")
    public ApiDataResult bookIncome(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Utils.logCalled("bookIncome", year+", "+ month);

        List<BookIncome> bookIncomes = mydataService.bookIncome(Utils.cid(), year, month);

        return responseService.result(bookIncomes);
    }

    @ApiOperation(value = "간편장부 지출 조회", notes = "간편장부에 입력한 지출을 월별로 조회한다.")
    @GetMapping(value = "/bookOutgoing")
    public ApiDataResult bookOutgoing(@RequestParam(required = false) Integer year, @RequestParam(required = false) Integer month) {
        Utils.logCalled("bookOutgoing", year+", "+ month);

        List<BookOutgoing> bookOutgoings = mydataService.bookOutgoing(Utils.cid(), year, month);

        return responseService.result(bookOutgoings);
    }


    @ApiOperation(value = "고객의 수입 간편장부 저장", notes = "고객이 수기로 수입정보를 간편장부에 저장")
    @PostMapping(value = "/saveBookIncome")
    public ApiDataResult saveBookIncome(@RequestBody BookIncomeVo bookIncomeVo) {
        Utils.logCalled("saveBookIncome", bookIncomeVo);
        bookIncomeVo.setCid(Utils.cid());

        mydataService.saveBookIncome(bookIncomeVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "고객의 지출 간편장부 저장", notes = "고객이 수기로 지출정보를 간편장부에 저장")
    @PostMapping(value = "/saveBookOutgoing")
    public ApiDataResult saveBookOutgoing(@RequestBody BookOutgoingVo bookOutgoingVo) {
        Utils.logCalled("saveBookOutgoing", bookOutgoingVo);
        bookOutgoingVo.setCid(Utils.cid());

        mydataService.saveBookOutgoing(bookOutgoingVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "마이데이터 수입데이터 수집", notes = "마이데이터 수입데이터를 수집한다.")
    @PostMapping(value = "/batchDataJob")
    public ApiDataResult batchDataJob() {
        Utils.logCalled("batchIncomeJob", "");

        mydataService.batchDataJob();

        return responseService.successResult();
    }

}
