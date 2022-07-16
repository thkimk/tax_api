package com.hanwha.tax.apiserver.service;


import com.hanwha.tax.apiserver.advice.exception.UserNotFoundException;
import com.hanwha.tax.apiserver.config.security.JwtTokenProvider;
import com.hanwha.tax.apiserver.dto.*;
import com.hanwha.tax.apiserver.entity.*;
import com.hanwha.tax.apiserver.repository.*;
import com.hanwha.tax.apiserver.vo.*;
import kcb.module.v3.exception.OkCertException;
import kcb.org.json.JSONObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    @Autowired
    DevInfoRepository devInfoRepository;

    @Autowired
    LoginHstRepository loginHstRepository;

    @Autowired
    CustTermsAgmtRepository custTermsAgmtRepository;

    @Autowired
    NotiSettingRepository notiSettingRepository;


    public String genCid() {
        String custId = custRepository.selectLastCid();

        String yymm = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMM"));
        if (custId == null) {
            return yymm.concat("000001");
        }

        int lastId = Integer.parseInt(custId.substring(4));
        String tmp = String.format("%06d", lastId+1);

        return yymm.concat(tmp);
    }


    public SignupDto signup(SignupVo signupVo) {
        SignupDto signupDto = new SignupDto();
        Cust cust = null;

        // cust 저장
        String cid = authInfoRepository.selectCidByCi(signupVo.getCi());
        if (cid == null) {
            // cust 저장
            cid = genCid();
            signupVo.setCid(cid);

            cust = new Cust(signupVo);
            custRepository.save(cust);

            // cust_info 저장
            CustInfo custInfo = new CustInfo(signupVo);
            custInfoRepository.save(custInfo);

            // cust_info_dtl
            CustInfoDtl custInfoDtl = new CustInfoDtl(signupVo);
            custInfoDtlRepository.save(custInfoDtl);

            // dev_info
            DevInfo devInfo = new DevInfo(signupVo);
            devInfoRepository.save(devInfo);

            // CustTermsAgmt 저장 (약관동의에서, 서비스/필수/선택 약관에 대한 동의사항)
            List<CustTermsAgmt> custTermsAgmts = CustTermsAgmt.custTermsAgmts(signupVo);
            custTermsAgmtRepository.saveAll(custTermsAgmts);

            // NotiSetting 저장 (약관동의에서, 푸시/SMS/이메일/알림톡 수신에 대한 동의사항)
            NotiSetting notiSetting = new NotiSetting(signupVo);
            notiSettingRepository.save(notiSetting);

            // auth_info 저장
            AuthInfo authInfo = new AuthInfo(signupVo);
            authInfoRepository.save(authInfo);

        } else {
            signupVo.setCid(cid);
            cust = custRepository.findByCid(cid);

            CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCid(cid);
            SignupDto.Additional additional = new SignupDto.Additional(custInfoDtl);
            signupDto.setAdditional(additional);
        }
        MDC.put("cid", cid);

        // JWT 토큰 생성 및 저장
        String jwt = jwtTokenProvider.createToken(cid);

        // return
        signupDto.setJwt(jwt);
        signupDto.setCustGrade(cust.getCustGrade());
        signupDto.setCustStatus(cust.getCustStatus());
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
        String custId = authInfoRepository.selectCidByCi(signupRegVo.getCi())    ;
        signupRegVo.setCid(custId);

        // cust 업데이트 (cust_grade: 준회원 --> 정회원)
        Cust cust = new Cust(signupRegVo);
        custRepository.save(cust);

    }


    public LoginDto login(LoginVo loginVo) {
        // auth_info에서 pin번호 비교 (cust_id가 아이디, pin번호가 패스워드 역할)
        AuthInfo authInfo = authInfoRepository.findByCid(loginVo.getCid());
        if (authInfo == null) {
            throw new UserNotFoundException();
        } else if (!authInfo.getPin().equals(loginVo.getPin())) {
            throw new UserNotFoundException();
        }

        // JWT 토큰 생성 및 저장
        Cust cust = custRepository.findByCid(loginVo.getCid());
        String jwt = jwtTokenProvider.createToken(loginVo.getCid());
        LoginDto loginDto = new LoginDto();
        loginDto.fillCust(cust);
        loginDto.setJwt(jwt);

        // taxFlag 계산
        loginDto.fillTaxFlag(null, null, '0');

        // login_hst에 이력 저장
        LoginHst loginHst = new LoginHst(loginVo);
        loginHstRepository.save(loginHst);

        return loginDto;
    }


    public void saveAuth(SaveAuthVo saveAuthVo) {
        String cid = MDC.get("cid");
        AuthInfo authInfo = authInfoRepository.findByCid(cid);
        if (authInfo == null) {
            throw new UserNotFoundException();
        }

        // auth_info 저장
        authInfo.fill(saveAuthVo);
        authInfoRepository.save(authInfo);

/*
        // return
        SaveAuthDto saveAuthDto = new SaveAuthDto();

        saveAuthDto.setCid(custId);
        saveAuthDto.setIsSucc('Y');
        saveAuthDto.setFailMsg("success");
*/
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

