package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.dto.MainMenuDto;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
import com.hanwha.tax.apiserver.service.CustService;
import com.hanwha.tax.apiserver.service.MainService;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.vo.SaveFamilyVo;
import com.hanwha.tax.apiserver.vo.SaveJobVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Main"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.PRE_ADDRESS + "main")
public class MainController {

    @Autowired
    ResponseService responseService;

    @Autowired
    MainService mainService;

    @ApiOperation(value = "메인 List", notes = "고객 등급에 따른 메인 정보를 제공한다.")
    @GetMapping(value = "/list")
    public ApiDataResult getMainList(Pageable pageable) {
        Utils.logCalled("main/mainMenu", "");

        return responseService.result(mainService.getMainList(Utils.convertPageable(pageable)));
    }

}