package com.example.apiserver.service;


import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.config.security.JwtTokenProvider;
import com.example.apiserver.dto.LoginDto;
import com.example.apiserver.dto.SaveAuthDto;
import com.example.apiserver.dto.SignupDto;
import com.example.apiserver.entity.AuthInfo;
import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.repository.AuthInfoRepository;
import com.example.apiserver.repository.CustRepository;
import com.example.apiserver.vo.LoginVo;
import com.example.apiserver.vo.SaveAuthVo;
import com.example.apiserver.vo.SignupVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    @Autowired
    CustRepository custRepository;

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public SignupDto signup(SignupVo signupVo) {
        SignupDto signupDto = new SignupDto();

        // cust 저장
//        Cust cust = new Cust(signupVo);
//        custRepository.save(cust);

        // cust_info 저장
//        CustInfo cust = new CustInfo(signupVo);
//        custRepository.save(cust);

        // terms_hst 저장

        // auth_info 저장

        return signupDto;
    }


    public LoginDto login(LoginVo loginVo) {
        // auth_info에서 pin번호 비교 (cust_id가 아이디, pin번호가 패스워드 역할)
/*
        AuthInfo authInfo = authInfoRepository.findByCustId(loginVo.getCust_id());
        if (authInfo == null) {
            throw new UserNotFoundException();
        } else if (!authInfo.getPin().equals(loginVo.getPin())) {
            throw new UserNotFoundException();
        }
*/

        // JWT 토큰 생성 및 저장
//        String jwtToken = jwtTokenProvider.createToken(authInfo.getCustId(), "00");
        String jwtToken = jwtTokenProvider.createToken("123456789", "00");
        LoginDto loginDto = new LoginDto();
        loginDto.setJwtToken(jwtToken);

        // login_hst에 이력 저장

        return loginDto;
    }


    public SaveAuthDto saveAuth(SaveAuthVo saveAuthVo) {
        return null;
    }

}
