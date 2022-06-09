package com.example.apiserver.service;


import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.config.security.JwtTokenProvider;
import com.example.apiserver.dto.LoginDto;
import com.example.apiserver.dto.SaveAuthDto;
import com.example.apiserver.dto.SignupDto;
import com.example.apiserver.entity.AuthInfo;
import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.entity.CustTermsAgmt;
import com.example.apiserver.repository.AuthInfoRepository;
import com.example.apiserver.repository.CustInfoRepository;
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
    CustInfoRepository custInfoRepository;

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;


    public SignupDto signup(SignupVo signupVo) {
        SignupDto signupDto = new SignupDto();

        // cust 저장
        Cust cust = new Cust(signupVo);
        custRepository.save(cust);

        // cust_info 저장
        CustInfo custInfo = new CustInfo(signupVo, cust.getCustId());
        custInfoRepository.save(custInfo);

        // CustTermsAgmt 저장?? 필요한가~
//        CustTermsAgmt custTermsAgmt = new CustTermsAgmt(signupVo);

        // auth_info 저장
        AuthInfo authInfo = new AuthInfo(signupVo, cust.getCustId());
        authInfoRepository.save(authInfo);

        // return
        signupDto.setCustId(cust.getCustId());
        signupDto.setIsSucc('Y');
        signupDto.setFailMsg("success");
        return signupDto;
/*

        userJpaRepo.save(User.builder()
                .uid(id)
                .password(passwordEncoder.encode(password))
                .name(name)
                .roles(Collections.singletonList("ROLE_USER"))
                .build());
        return responseService.successResult();
*/    }


    public LoginDto login(LoginVo loginVo) {
        // auth_info에서 pin번호 비교 (cust_id가 아이디, pin번호가 패스워드 역할)
        AuthInfo authInfo = authInfoRepository.findByCustId(loginVo.getCustId());
        if (authInfo == null) {
            throw new UserNotFoundException();
        } else if (!authInfo.getPin().equals(loginVo.getPin())) {
            throw new UserNotFoundException();
        }

        // JWT 토큰 생성 및 저장
        Cust cust = custRepository.findByCustId(loginVo.getCustId());
        String jwtToken = jwtTokenProvider.createToken(authInfo.getCustId(), "00");
        LoginDto loginDto = new LoginDto();
        loginDto.fillCust(cust);
        loginDto.setJwtToken(jwtToken);

        // login_hst에 이력 저장

        return loginDto;
    }


    public SaveAuthDto saveAuth(SaveAuthVo saveAuthVo) {
        AuthInfo authInfo = authInfoRepository.findByCustId(saveAuthVo.getCustId());
        if (authInfo == null) {
            throw new UserNotFoundException();
        } else if (!authInfo.getPin().equals(saveAuthVo.getPin())) {
            throw new UserNotFoundException();
        }

        // cust_info 저장
        CustInfo custInfo = new CustInfo(saveAuthVo);
        custInfoRepository.save(custInfo);

        // auth_info 저장
        authInfo = new AuthInfo(saveAuthVo);
        authInfoRepository.save(authInfo);

        // return
        SaveAuthDto saveAuthDto = new SaveAuthDto();

        saveAuthDto.setCustId(saveAuthVo.getCustId());
        saveAuthDto.setIsSucc('Y');
        saveAuthDto.setFailMsg("success");
        return saveAuthDto;
    }

}
