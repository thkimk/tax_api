package com.hanwha.tax.apiserver.service;


import com.hanwha.tax.apiserver.Constants;
import com.hanwha.tax.apiserver.advice.exception.UserNotFoundException;
import com.hanwha.tax.apiserver.config.security.JwtTokenProvider;
import com.hanwha.tax.apiserver.dto.*;
import com.hanwha.tax.apiserver.entity.*;
import com.hanwha.tax.apiserver.model.Kcb;
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
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    private final Kcb kcb;


    public String genCid() {
        final String custId = custRepository.selectLastCid();

        final String yymm = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMM"));
        if (custId == null) {
            return yymm.concat("000001");
        }

        final int lastId = Integer.parseInt(custId.substring(4));
        final String tmp = String.format("%06d", lastId + 1);

        return yymm.concat(tmp);
    }


    @Transactional
    public MemberDto signup(SignupVo signupVo) {
        final MemberDto memberDto = new MemberDto();
        final UserDto userDto;

        final UserInterface userInterface = authInfoRepository.selectUserByCi(signupVo.getCi());
        if (userInterface == null) { //준회원 생성
            // cust 저장
            final String cid = genCid();
            signupVo.setCid(cid);
            userDto = new UserDto(signupVo);
            userDto.setCid(cid);

            final Cust cust = new Cust(signupVo);
            custRepository.save(cust);

            userDto.setGrade(cust.getCustGrade());
            userDto.setStatus(cust.getCustStatus());
        } else { //준회원 이상 조회
            userDto = new UserDto(userInterface);
            final CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCid(userDto.getCid());
            userDto.setAdditional(custInfoDtl);
            signupVo.setCid(userDto.getCid());
        }
        MDC.put("cid", userDto.getCid());

        saveCustInfos(signupVo);

        // JWT 토큰 생성 및 저장
        final String jwt = jwtTokenProvider.createToken(userDto.getCid());

        memberDto.setJwt(jwt);
        memberDto.setUser(userDto);
        return memberDto;
    }

    @Transactional
    private void saveCustInfos(SignupVo signupVo) {
        // cust_info 저장
        final CustInfo custInfo = new CustInfo(signupVo);
        custInfoRepository.save(custInfo);
        // dev_info
        final DevInfo devInfo = new DevInfo(signupVo);
        devInfoRepository.save(devInfo);
        // CustTermsAgmt 저장 (약관동의에서, 서비스/필수/선택 약관에 대한 동의사항)
        final List<CustTermsAgmt> custTermsAgmts = CustTermsAgmt.custTermsAgmts(signupVo);
        custTermsAgmtRepository.saveAll(custTermsAgmts);
        // NotiSetting 저장 (약관동의에서, 푸시/SMS/이메일/알림톡 수신에 대한 동의사항)
        final NotiSetting notiSetting = new NotiSetting(signupVo);
        notiSettingRepository.save(notiSetting);
        // auth_info 저장
        final AuthInfo authInfo = new AuthInfo(signupVo);
        authInfoRepository.save(authInfo);
    }


    public void signupReg(SignupRegVo signupRegVo) {
        String cid = authInfoRepository.selectCidByCi(signupRegVo.getCi());
        signupRegVo.setCid(cid);

        // cust 업데이트 (cust_grade: 준회원 --> 정회원)
        Cust cust = new Cust(signupRegVo);
        custRepository.save(cust);

        log.info("## signupReg: ---> Regular member was added ({})", cid);
    }


    public void signoutReg(SignoutRegVo signoutRegVo) {
        String cid = authInfoRepository.selectCidByCi(signoutRegVo.getCi());
        signoutRegVo.setCid(cid);

        // cust 업데이트 (cust_grade: 준회원 --> 정회원)
        Cust cust = new Cust(signoutRegVo);
        custRepository.save(cust);

        log.info("## signoutReg: ---> Regular member was removed ({})", cid);
    }


    @Transactional
    public MemberDto login(LoginVo loginVo) {
        final String cid = loginVo.getCid();
        // auth_info에서 pin번호 비교 (cust_id가 아이디, pin번호가 패스워드 역할)
        AuthInfo authInfo = authInfoRepository.findByCid(cid);
        if (authInfo == null) {
            throw new UserNotFoundException();
        } else if (!authInfo.getPin().equals(loginVo.getPin())) {
            throw new UserNotFoundException();
        }

        // JWT 토큰 생성 및 저장
        final UserInterface userInterface = authInfoRepository.selectUserByCid(cid);
        final CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCid(cid);
        final String jwt = jwtTokenProvider.createToken(cid);

        final UserDto userDto = new UserDto(userInterface);
        userDto.setAdditional(custInfoDtl);

        final MemberDto memberDto = new MemberDto();
        memberDto.setJwt(jwt);
        memberDto.setUser(userDto);

//        // taxFlag 계산
//        loginDto.fillTaxFlag(null, null, '0');

        final DevInfo devInfo = new DevInfo(loginVo);
        devInfoRepository.save(devInfo);

        // authStatus 상태 초기화
        authInfo.setAuthStatus(Constants.CUST_ST_NORMAL);
        authInfoRepository.save(authInfo);

        // login_hst에 이력 저장
        LoginHst loginHst = new LoginHst(loginVo);
        loginHstRepository.save(loginHst);

        return memberDto;
    }


    public void logout(String cid) {
        AuthInfo authInfo = authInfoRepository.findByCid(cid);
        if (authInfo == null) {
            throw new UserNotFoundException();
        }

        // auth_info 저장
        authInfo.setAuthStatus(Constants.CUST_ST_SIGNOUT);
        authInfoRepository.save(authInfo);

        // login_hst에 이력 저장
        log.info("## logout : cid {}, {}", cid, LocalDateTime.now());
//        LoginHst loginHst = new LoginHst(loginVo);
//        loginHstRepository.save(loginHst);
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
            reqJson.put("SEX_CD", idenOtpReqVo.getSexCd().toCharacter());         // 성별 (M, F)
            reqJson.put("NTV_FRNR_CD", String.valueOf(idenOtpReqVo.getFrnrCd()));   // 내외국인구분 (L, F)
            reqJson.put("TEL_COM_CD", idenOtpReqVo.getTelComCd());  // 통신사코드
            reqJson.put("TEL_NO", idenOtpReqVo.getTelNo());         // 휴대폰번호
            reqJson.put("USER_IP", "10.0.0.1");
            reqJson.put("SITE_URL", "www.taxsol.co.kr");
            reqJson.put("SITE_NAME", "TaxSolution");
            reqJson.put("RQST_CAUS_CD", "00");                      // 인증요청사유코드 (00:회원가입, 01:성인인증, 02:회원정보수정, 03:비밀번호찾기, 04:상품구매, 99:기타)
            reqJson.put("CHNL_CD", "");                             // Optional
            reqJson.put("APP_HASH_STR", "iEEEN/CqECx");                        // Optional

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
        log.debug("## reqJson : {}", reqJson.toString());

        idenOtpReqDto.fill(kcb.callKcb("IDS_HS_EMBED_SMS_REQ", reqJson.toString()));
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
        log.debug("## reqJson : {}", reqJson.toString());

        idenOtpConfirmDto.fill(kcb.callKcb("IDS_HS_EMBED_SMS_CIDI", reqJson.toString()));
        return idenOtpConfirmDto;
    }

}

