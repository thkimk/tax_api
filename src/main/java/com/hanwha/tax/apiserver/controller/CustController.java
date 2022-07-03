package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
//import com.hanwha.tax.apiserver.entity.User;
import com.hanwha.tax.apiserver.Utils;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;

//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.service.CustService;
import com.hanwha.tax.apiserver.vo.SaveFamilyVo;
import com.hanwha.tax.apiserver.vo.SaveJobVo;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

//@PreAuthorize("hasRole('ROLE_USER')") //추가내용
@Api(tags = {"User 사용자"}) // UserController를 대표하는 최상단 타이틀 영역에 표시될 값 세팅
@RequiredArgsConstructor // class 내부의 final 객체는 Constructor Injection 수행, @Autowired도 가능
@RestController // 결과를 JSON으로 도출
@RequestMapping(value = Constants.API +"/v1"+ "/cust") // api resource를 버전별로 관리, /v1 을 모든 리소스 주소에 적용
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
        Utils.logCalled("saveJob", saveJobVo);
        saveJobVo.setCustId(Utils.custId());

        // cust_info_dtl 업데이트 필요
        custService.saveJob(saveJobVo);

        return responseService.result(new String("saveJob result"));
    }


    @ApiOperation(value = "부양가족 저장", notes = "사용자의 부양가족 정보를 저장한다.")
    @PostMapping(value = "/saveFamily")
    public ApiDataResult saveFamily(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody SaveFamilyVo saveFamilyVo) {
        Utils.logCalled("saveFamily", saveFamilyVo);
        saveFamilyVo.setCustId(Utils.custId());

        // cust_info_dtl 업데이트 필요
        custService.saveFamily(saveFamilyVo);

        return responseService.result(new String("saveJob result"));
    }

}