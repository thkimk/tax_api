package com.example.apiserver.service;


import com.example.apiserver.Constants;
import com.example.apiserver.advice.exception.UserNotFoundException;
import com.example.apiserver.config.security.JwtTokenProvider;
import com.example.apiserver.dto.*;
import com.example.apiserver.entity.*;
import com.example.apiserver.repository.AuthInfoRepository;
import com.example.apiserver.repository.CustInfoDtlRepository;
import com.example.apiserver.repository.CustInfoRepository;
import com.example.apiserver.repository.CustRepository;
import com.example.apiserver.vo.*;
import kcb.module.v3.exception.OkCertException;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    @Value("${tax.kcbMemberKey}")
    private String KCB_MEMBER_KEY = "";

    @Value("${tax.licence}")
    private String KCB_LICENCE = "";

    @Autowired
    CustRepository custRepository;

    @Autowired
    CustInfoRepository custInfoRepository;

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    CustInfoDtlRepository custInfoDtlRepository;


    public String genCustId() {
        String custId = custRepository.getLastCustId();
        int currentId = 0;
        String yymm = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMM"));
        if (custId.startsWith(yymm)) {
            currentId = Integer.parseInt(custId.substring(4));
        }

        String rand = String.format("%06d", currentId+1);
        return yymm.concat(rand);
    }


    public SignupDto signup(SignupVo signupVo) {
        SignupDto signupDto = new SignupDto();

        // cust 저장
        String custId = authInfoRepository.getCustIdByCi(signupVo.getCi());
        if (custId == null) {
            custId = genCustId();
            Cust cust = new Cust(signupVo, custId);
            custRepository.save(cust);

            // cust_info 저장
            CustInfo custInfo = new CustInfo(signupVo, custId);
            custInfoRepository.save(custInfo);

            // cust_info_dtl
            CustInfoDtl custInfoDtl = new CustInfoDtl(signupVo, custId);
            custInfoDtlRepository.save(custInfoDtl);

            // CustTermsAgmt 저장?? 필요한가~
//        CustTermsAgmt custTermsAgmt = new CustTermsAgmt(signupVo);

            // auth_info 저장
            AuthInfo authInfo = new AuthInfo(signupVo, custId);
            authInfoRepository.save(authInfo);
        } else {
            CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCustId(custId);
            SignupDto.Additional additional = new SignupDto.Additional(custInfoDtl);
            signupDto.setAdditional(additional);
        }

        // JWT 토큰 생성 및 저장
        String jwt = jwtTokenProvider.createToken(custId);

        // return
        signupDto.setJwt(jwt);
        SignupDto.User user = new SignupDto.User(signupVo);
        signupDto.setUser(user);

        return signupDto;
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


    public void signupReg(SignupRegVo signupRegVo) {
        SignupDto signupDto = new SignupDto();

        // cust 업데이트 (cust_grade: 준회원 --> 정회원)
        Cust cust = new Cust(signupRegVo);
        custRepository.save(cust);

    }


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
        String jwt = jwtTokenProvider.createToken(authInfo.getCustId());
        LoginDto loginDto = new LoginDto();
        loginDto.fillCust(cust);
        loginDto.setJwt(jwt);

        // login_hst에 이력 저장

        return loginDto;
    }


    public SaveAuthDto saveAuth(SaveAuthVo saveAuthVo) {
        String custId = MDC.get("custId");
        AuthInfo authInfo = authInfoRepository.findByCustId(custId);
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

        saveAuthDto.setCustId(custId);
        saveAuthDto.setIsSucc('Y');
        saveAuthDto.setFailMsg("success");
        return saveAuthDto;
    }


    public IdenOtpReqDto idenOtpReq(IdenOtpReqVo idenOtpReqVo) {
        IdenOtpReqDto idenOtpReqDto = new IdenOtpReqDto();
        JSONObject reqJson = new JSONObject();
        {
            // 유효성검증
/*
            if(!NAME.matches("^[가-힝a-zA-Z ]*")) {out.print("성명에 유효하지 않은 문자열이 있습니다."); return;}	// EUC-KR인 경우
            if(!BIRTHDAY.matches("^[0-9x]*")) {out.print("생년월일에 유효하지 않은 문자열이 있습니다."); return;}
            if(!SEX_CD.matches("^[MFx]")) {out.print("성별에 유효하지 않은 문자열이 있습니다."); return;}
            if(!NTV_FRNR_CD.matches("^[LFx]")) {out.print("내외국인구분에 유효하지 않은 문자열이 있습니다."); return;}
            if(!TEL_COM_CD.matches("^[0-9x]*")) {out.print("통신사코드에 유효하지 않은 문자열이 있습니다."); return;}
            if(!TEL_NO.matches("^[0-9]*")) {out.print("휴대폰번호에 유효하지 않은 문자열이 있습니다."); return;}
            if(!RQST_CAUS_CD.matches("^[0-9x]*")) {out.print("인증요청사유코드에 유효하지 않은 문자열이 있습니다."); return;}
            if(!SMS_RESEND_YN.matches("^[YN]")) {out.print("재전송여부에 유효하지 않은 문자열이 있습니다."); return;}
            if(!TX_SEQ_NO.matches("^[0-9a-zA-Z]*")) {out.print("거래고유번호에 유효하지 않은 문자열이 있습니다."); return;}
*/
//            try {
//                reqJson.put("NAME", idenOtpReqVo.getName().getBytes("8859_1")); // 성명
//            } catch (UnsupportedEncodingException e) {}

            reqJson.put("NAME", idenOtpReqVo.getName()); // 성명
            reqJson.put("BIRTHDAY", idenOtpReqVo.getBirth());       // 생년월일
            reqJson.put("SEX_CD", String.valueOf(idenOtpReqVo.getSexCd()));         // 성별 (M, F)
            reqJson.put("NTV_FRNR_CD", String.valueOf(idenOtpReqVo.getFrnrCd()));   // 내외국인구분 (L, F)
            reqJson.put("TEL_COM_CD", idenOtpReqVo.getTelComCd());  // 통신사코드
            reqJson.put("TEL_NO", idenOtpReqVo.getTelNo());         // 휴대폰번호
            reqJson.put("USER_IP", "10.0.0.1");
            reqJson.put("SITE_URL", "www.test.co.kr:normal");
            reqJson.put("SITE_NAME", "test.co.kr:normal");
            reqJson.put("RQST_CAUS_CD", "00");                      // 인증요청사유코드 (00:회원가입, 01:성인인증, 02:회원정보수정, 03:비밀번호찾기, 04:상품구매, 99:기타)
            reqJson.put("CHNL_CD", "");                             // Optional
            reqJson.put("APP_HASH_STR", "");                        // Optional

            if (idenOtpReqVo.getTxSeqNo() != null) {
                reqJson.put("TX_SEQ_NO", idenOtpReqVo.getTxSeqNo());       // 거래고유번호. 동일문자열을 두번 사용할 수 없음.
                reqJson.put("SMS_RESEND_YN", "Y");
            } else {
                reqJson.put("SMS_RESEND_YN", "N");
            }

            reqJson.put("AGREE1", "Y");
            reqJson.put("AGREE2", "Y");
            reqJson.put("AGREE3", "Y");
            reqJson.put("AGREE4", "Y");
        }
        log.info("## reqJson : {}", reqJson.toString());

        JSONObject resJson = null;
        try {
            kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
            String resultStr = okcert.callOkCert("PROD", KCB_MEMBER_KEY, "IDS_HS_EMBED_SMS_REQ", KCB_LICENCE, reqJson.toString());

            if (resultStr != null) {
                log.info("## resJson : {}", resultStr);
                resJson = new JSONObject(resultStr);
                idenOtpReqDto.fill(resJson);
            }
        } catch (OkCertException e) {}

        return idenOtpReqDto;
    }


    public IdenOtpConfirmDto idenOtpConfirm(IdenOtpConfirmVo idenOtpConfirmVo) {
        IdenOtpConfirmDto idenOtpConfirmDto = new IdenOtpConfirmDto();
        JSONObject reqJson = new JSONObject();
        {
            reqJson.put("TX_SEQ_NO", idenOtpConfirmVo.getTxSeqNo());
            reqJson.put("TEL_NO", idenOtpConfirmVo.getTelNo());
            reqJson.put("OTP_NO", idenOtpConfirmVo.getOtpNo());
        }
        log.info("## reqJson : {}", reqJson.toString());

        JSONObject resJson = null;
        try {
            kcb.module.v3.OkCert okcert = new kcb.module.v3.OkCert();
            String resultStr = okcert.callOkCert("PROD", KCB_MEMBER_KEY, "IDS_HS_EMBED_SMS_CIDI", KCB_LICENCE, reqJson.toString());

            if (resultStr != null) {
                log.info("## resJson : {}", resultStr);
                resJson = new JSONObject(resultStr);
                idenOtpConfirmDto.fill(resJson);
            }
        } catch (OkCertException e) {}

        return idenOtpConfirmDto;
    }

}

