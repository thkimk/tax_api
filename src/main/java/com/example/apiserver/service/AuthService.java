package com.example.apiserver.service;


import com.example.apiserver.dto.SignupDto;
import com.example.apiserver.entity.Cust;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.repository.CustRepository;
import com.example.apiserver.vo.SignupVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    @Autowired
    CustRepository custRepository;


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
}
