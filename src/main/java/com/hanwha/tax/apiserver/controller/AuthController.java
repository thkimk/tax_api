package com.hanwha.tax.apiserver.controller;

import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.util.Utils;
import com.hanwha.tax.apiserver.advice.exception.AuthenticationEntryPointException;
import com.hanwha.tax.apiserver.config.security.JwtTokenProvider;
import com.hanwha.tax.apiserver.dto.*;
//import com.hanwha.tax.apiserver.entity.User;
import com.hanwha.tax.apiserver.model.response.ApiDataResult;
//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.service.AuthService;
import com.hanwha.tax.apiserver.service.ResponseService;
import com.hanwha.tax.apiserver.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"Authority 인증"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value =Constants.PRE_ADDRESS + "auth")
@Slf4j
public class AuthController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    @Autowired
    AuthService authService;

    @Value("${tax.coocon_token}")
    private String taxToken;

//    private final UserJpaRepository userJpaRepo; // jpa 쿼리 활용
    private final JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/login")
    public ApiDataResult login(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody LoginVo loginVo) {
        Utils.logCalled("auth/login", loginVo);
        loginVo.setCid(Utils.cid());
/*
        User user = userJpaRepo.findByUid(id).orElseThrow(EmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new EmailSigninFailedException();

        return responseService.result(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
*/
        final MemberDto memberDto = authService.login(loginVo);
        return responseService.result(memberDto);
    }

    @ApiOperation(value = "로그아웃", notes = "이메일 회원 로그아웃을 한다.")
    @PostMapping(value = "/logout")
    public ApiDataResult logout() {
        Utils.logCalled("auth/logout", null);

        authService.logout(Utils.cid());
        return responseService.successResult();
    }


    @ApiOperation(value = "회원 가입(준회원)", notes = "준회원 회원가입 처리를 한다.")
    @PostMapping(value = "/signup")
    public ApiDataResult signup(@ApiParam(value = "회원가입에 필요한 정보를 전달하고, 회원가입 처리", required = true) @RequestBody SignupVo signupVo) {
        Utils.logCalled("auth/signup", signupVo);

        final MemberDto memberDto = authService.signup(signupVo);
        return responseService.result(memberDto);
/*

        userJpaRepo.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.successResult();
*/
    }


    @ApiOperation(value = "회원 가입(정회원)", notes = "[쿠콘 역호출]정회원 가입 처리를 한다.(Regular)")
    @PostMapping(value = "/signupReg")
    public ApiDataResult signupReg(@ApiParam(value = "회원가입에 필요한 정보를 전달하고, 회원가입 처리", required = true) @RequestBody SignupRegVo signupRegVo) {
        Utils.logCalled("auth/signupReg", signupRegVo);
        if (!signupRegVo.getTaxToken().equals(taxToken)) {
            throw new AuthenticationEntryPointException();
        }

        authService.signupReg(signupRegVo);
        return responseService.successResult();
    }

    @ApiOperation(value = "회원 탈퇴(정회원)", notes = "[쿠콘 역호출] 정회원 탈퇴 처리를 한다.(Regular)")
    @PostMapping(value = "/signoutReg")
    public ApiDataResult signoutReg(@ApiParam(value = "회원가입에 필요한 정보를 전달하고, 회원탈퇴 처리", required = true) @RequestBody SignoutRegVo signoutRegVo) {
        Utils.logCalled("auth/signoutReg", signoutRegVo);
        if (!signoutRegVo.getTaxToken().equals(taxToken)) {
            throw new AuthenticationEntryPointException();
        }

        authService.signoutReg(signoutRegVo);
        return responseService.successResult();
    }

/*
    @ApiOperation(value = "본인 확인", notes = "본인확인 인증 토큰으로 최종 확정한다.")
    @PostMapping(value = "/verify")
    public ApiDataResult verify(@RequestBody VerifyVo verifyVo) {
        VerifyDto verifyDto = new VerifyDto();
        return responseService.result(verifyDto);
    }
*/

    @ApiOperation(value = "비밀번호 재등록", notes = "사용자의 비밀번호를 재등록한다.")
    @PostMapping(value = "/saveAuth")
    public ApiDataResult saveAuth(@RequestBody SaveAuthVo saveAuthVo) {
        Utils.logCalled("auth/saveAuth", saveAuthVo);

        authService.saveAuth(saveAuthVo);

        return responseService.successResult();
    }


    @ApiOperation(value = "본인확인 OTP 요청", notes = "본인확인을 위한 OTP발송을 요청한다.")
    @PostMapping(value = "/idenOtpReq")
    public ApiDataResult idenOtpReq(@RequestBody IdenOtpReqVo idenOtpReqVo) {
        Utils.logCalled("auth/idenOtpReq", idenOtpReqVo);

        if (idenOtpReqVo.getTxSeqNo() != null) idenOtpReqVo.setTxSeqNo(null);

        IdenOtpReqDto idenOtpReqDto = authService.idenOtpReq(idenOtpReqVo);
        if (!idenOtpReqDto.getRsltCd().equals(Constants.KCB_RES_B000)) {
            return responseService.failResult();
        }

        return responseService.result(idenOtpReqDto);
    }

    @ApiOperation(value = "본인확인 OTP 재요청", notes = "본인확인을 위한 OTP발송을 재요청한다.")
    @PostMapping(value = "/idenOtpReqRetry")
    public ApiDataResult idenOtpReqRetry(@RequestBody IdenOtpReqVo idenOtpReqVo) {
        Utils.logCalled("auth/idenOtpReqRetry", idenOtpReqVo);

        if (idenOtpReqVo.getTxSeqNo() == null) {
            return responseService.failResult();
        }

        IdenOtpReqDto idenOtpReqDto = authService.idenOtpReq(idenOtpReqVo);
        if (!idenOtpReqDto.getRsltCd().equals(Constants.KCB_RES_B000)) {
            return responseService.failResult();
        }

        return responseService.result(idenOtpReqDto);
    }

    @ApiOperation(value = "본인확인 OTP 확인", notes = "본인확인을 위한 OTP확인을 요청한다.")
    @PostMapping(value = "/idenOtpConfirm")
    public ApiDataResult idenOtpConfirm(@RequestBody IdenOtpConfirmVo idenOtpConfirmVo) {
        Utils.logCalled("auth/idenOtpConfirm", idenOtpConfirmVo);

        IdenOtpConfirmDto idenOtpConfirmDto = authService.idenOtpConfirm(idenOtpConfirmVo);
        if (!idenOtpConfirmDto.getRsltCd().equals(Constants.KCB_RES_B000)) {
            return responseService.failResult();
        }

        return responseService.result(idenOtpConfirmDto);
    }

}