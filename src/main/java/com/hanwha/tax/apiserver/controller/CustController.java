package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
//import com.hanwha.tax.apiserver.entity.User;
import com.hanwha.tax.apiserver.dto.AnswerDto;
import com.hanwha.tax.apiserver.dto.CustTermsDto;
import com.hanwha.tax.apiserver.dto.MemberDto;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;

//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.service.CustService;
import com.hanwha.tax.apiserver.vo.AskVo;
import com.hanwha.tax.apiserver.vo.DeductVo;
import com.hanwha.tax.apiserver.vo.SaveFamilyVo;
import com.hanwha.tax.apiserver.vo.SaveJobVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@PreAuthorize("hasRole('ROLE_USER')") //추가내용
@Api(tags = {"User 사용자"}) // UserController를 대표하는 최상단 타이틀 영역에 표시될 값 세팅
@RequiredArgsConstructor // class 내부의 final 객체는 Constructor Injection 수행, @Autowired도 가능
@RestController // 결과를 JSON으로 도출
@RequestMapping(value = Constants.PRE_ADDRESS + "cust") // api resource를 버전별로 관리, /v1 을 모든 리소스 주소에 적용
@CrossOrigin    //kkk
public class CustController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    CustService custService;

//    private final UserJpaRepository userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능
//    private UserService userService;
/*

    @Secured("ROLE_USER")
    @ApiOperation(value = "회원 리스트 조회", notes = "모든 회원을 조회한다.") // 각각의 resource에 제목과 설명 표시
    @GetMapping(value = "/users") // user 테이블의 모든 정보를 읽어옴
    public ApiDataResult findAllUser() { // 데이터가 1개 이상일 수 있기에 List<User>로 선언
        return responseService.result(userJpaRepo.findAll()); // JPA를 사용하면 CRUD에 대해 설정 없이 쿼리 사용 가능 (select * from user 와 같음)
        //결과 데이터가 여러개인 경우 getListResult 활용
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @ApiOperation(value = "회원 단건 조회", notes = "회원번호(msrl)로 회원을 조회한다.")
    @GetMapping(value = "/user")
    public ApiDataResult findUserById() {
        // SecurityContext에서 인증 받은 회원의 정보를 얻어온다.
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String id = authentication.getName();
        return responseService.result(userJpaRepo.findByUid(id).orElseThrow(UserNotFoundException::new));
        // 결과 데이터가 단일건인 경우 getSingleResult를 이용하여 결과를 출력
    }

    @ApiOperation(value = "회원 수정", notes = "회원정보를 수정한다.")
    @PutMapping(value = "/user")
    public ApiDataResult modify(
            @ApiParam(value = "회원번호", required = true) @RequestParam long msrl,
            @ApiParam(value = "회원이름", required = true) @RequestParam String name) {
        User user = User.builder()
                .msrl(msrl)
                .name(name)
                .build();

        return responseService.result(userJpaRepo.save(user));
    }

    @ApiOperation(value = "회원 삭제", notes = "msrl로 회원정보를 삭제한다.")
    @DeleteMapping(value = "/user/{msrl}")
    public ApiDataResult delete (
            @ApiParam(value = "회원정보", required = true) @PathVariable long msrl ) {
        userJpaRepo.deleteById(msrl); // deleteById id를 받아 delete query 실행
        return responseService.successResult();
        // 성공 결과 정보만 필요한 경우 getSuccessResult()를 이용하여 결과를 출력
    }

    @Secured("ROLE_USER")
    @ApiOperation(value = "회원 리스트 검색 조회", notes = "모든 회원을 검색 한다.") //
    @GetMapping("/user/search")
    public Page<UserDto> searchUser(UserVo condition, Pageable pageable) {
        System.out.println("111111111111111111 ===="+ pageable.getPageSize());
        System.out.println("222222222222222222 ===="+ pageable.getOffset());
        System.out.println("333333333333333333 ===="+ pageable.getPageNumber());
        return userJpaRepo.search(condition, pageable);
    }
*/


    @ApiOperation(value = "사용자 업종 선택", notes = "사용자의 업종을 선택해서 저장한다.")
    @PostMapping(value = "/saveJob")
    public ApiDataResult saveJob(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody SaveJobVo saveJobVo) {
        Utils.logCalled("cust/saveJob", saveJobVo);
        if (saveJobVo.getCid() == null) {
            saveJobVo.setCid(Utils.cid());
        }

        // cust_info_dtl 업데이트 필요
        custService.saveJob(saveJobVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "부양가족 저장", notes = "사용자의 부양가족 정보를 저장한다.")
    @PostMapping(value = "/saveFamily")
    public ApiDataResult saveFamily(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody SaveFamilyVo saveFamilyVo) {
        Utils.logCalled("cust/saveFamily", saveFamilyVo);
        saveFamilyVo.setCid(Utils.cid());

        // cust_info_dtl 업데이트 필요
        custService.saveFamily(saveFamilyVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "1:1문의", notes = "고객이 문의한 사항을 처리한다. (저장 및 CS담당자 전달)")
    @PostMapping(value = "/ask")
    public ApiDataResult ask(@RequestBody AskVo askVo) {
        Utils.logCalled("cust/ask", askVo);
        askVo.setCid(Utils.cid());

        custService.ask(askVo);

        return responseService.successResult();
    }

    @ApiOperation(value = "고객의 1:1문의 조회", notes = "고객이 문의한 1:1문의 내용과 답변 조회")
    @GetMapping(value = "/answer")
    public ApiDataResult answer(Pageable pageable) {
        Utils.logCalled("cust/answer", null);

        Page<AnswerDto> answerDtos = custService.answer(Utils.cid(), pageable);

        return responseService.result(answerDtos.getContent());
    }


    @ApiOperation(value = "고객의 상세정보 조회", notes = "회원가입시 고객이 제공한 정보 조회")
    @GetMapping(value = "/infos")
    public ApiDataResult infos() {
        Utils.logCalled("cust/infos", null);

        MemberDto memberDto = custService.infos(Utils.cid());

        return responseService.result(memberDto.getUser());
    }


    @ApiOperation(value = "고객의 공제정보 조회", notes = "회원가입시 고객이 제공한 공제정보 조회")
    @GetMapping(value = "/deduct")
    public ApiDataResult deduct(@ApiParam(value = "연도") @RequestParam(required = false) Integer year) {
        Utils.logCalled("cust/deduct", year);

        return responseService.result(custService.deduct(Utils.cid(), year));
    }


    @ApiOperation(value = "고객의 공제정보 저장", notes = "회원가입시 고객이 제공한 공제정보 저장")
    @PostMapping(value = "/saveDeduct")
    public ApiDataResult saveDeduct(@RequestBody DeductVo deductVo) {
        Utils.logCalled("cust/saveDeduct", deductVo);
        deductVo.setCid(Utils.cid());

        custService.saveDeduct(deductVo);

        return responseService.successResult();
    }

    @ApiOperation(value = "고객의 부양가족 정보 조회", notes = "고객이 제공한 부양가족 정보 조회")
    @GetMapping(value = "/family")
    public ApiDataResult family() {
        Utils.logCalled("cust/family", null);

        return responseService.result(custService.family(Utils.cid()));
    }


    @ApiOperation(value = "고객의 약관 및 수신 동의정보 조회", notes = "회원가입시 고객이 동의한 정보들 조회 (약관, 수신, 수신매체 등)")
    @GetMapping(value = "/terms")
    public ApiDataResult terms() {
        Utils.logCalled("cust/terms", null);

        CustTermsDto custTermsDto = custService.terms(Utils.cid());

        return responseService.result(custTermsDto);
    }


}