package com.example.apiserver.controller;

import com.example.apiserver.Constants;
import com.example.apiserver.advice.exception.EmailSigninFailedException;
import com.example.apiserver.config.security.JwtTokenProvider;
import com.example.apiserver.dto.AppInitsDto;
import com.example.apiserver.entity.User;
import com.example.apiserver.model.response.ApiDataResult;
import com.example.apiserver.repository.UserJpaRepository;
import com.example.apiserver.service.ResponseService;
import com.example.apiserver.vo.LoginVo;
import com.example.apiserver.vo.SignupVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Api(tags = {"Authority 인증"})
@RequiredArgsConstructor
@RestController
@RequestMapping(value = Constants.API + "/" + Constants.VERSION + "/auth")
public class AuthController {

    @Autowired
    ResponseService responseService; // API 요청 결과에 대한 code, messageㅍ

    private final UserJpaRepository userJpaRepo; // jpa 쿼리 활용
    private final JwtTokenProvider jwtTokenProvider; // jwt 토큰 생성
    private final PasswordEncoder passwordEncoder; // 비밀번호 암호화

    @ApiOperation(value = "로그인", notes = "이메일 회원 로그인을 한다.")
    @PostMapping(value = "/login")
    public ApiDataResult login(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody LoginVo loginVo) {
/*
        User user = userJpaRepo.findByUid(id).orElseThrow(EmailSigninFailedException::new);
        if (!passwordEncoder.matches(password, user.getPassword()))
            // matches : 평문, 암호문 패스워드 비교 후 boolean 결과 return
            throw new EmailSigninFailedException();

        return responseService.result(jwtTokenProvider.createToken(String.valueOf(user.getMsrl()), user.getRoles()));
*/
        return responseService.result(new String("login result"));
    }

    @ApiOperation(value = "회원 가입", notes = "회원가입 처리를 한다.")
    @PostMapping(value = "/signup")
    public ApiDataResult signup(@ApiParam(value = "회원ID : 이메일", required = true) @RequestBody SignupVo signupVo) {
        return responseService.result(new String("signup result"));
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


    @ApiOperation(value = "본인 확인", notes = "본인확인 인증 토큰으로 최종 확정한다.")
    @PostMapping(value = "/verify")
    public ApiDataResult verify() {
        return responseService.result(new String("verify result"));
    }

    @ApiOperation(value = "비밀번호 재등록", notes = "사용자의 비밀번호를 재등록한다.")
    @PostMapping(value = "/saveAuth")
    public ApiDataResult saveAuth() {
        return responseService.result(new String("saveAuth result"));
    }

}