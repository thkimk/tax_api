package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.Constants;
//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.advice.exception.UserNotFoundException;
import com.hanwha.tax.apiserver.dto.*;
import com.hanwha.tax.apiserver.entity.*;
import com.hanwha.tax.apiserver.entitysecond.MailHistory;
import com.hanwha.tax.apiserver.entitysecond.MailReceiver;
import com.hanwha.tax.apiserver.model.Finger;
import com.hanwha.tax.apiserver.repository.*;
import com.hanwha.tax.apiserver.repositorysecond.MailHistoryRepository;
import com.hanwha.tax.apiserver.repositorysecond.MailReceiverRepository;
import com.hanwha.tax.apiserver.util.DateUtil;
import com.hanwha.tax.apiserver.vo.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CustService {
    @Autowired
    CustInfoDtlRepository custInfoDtlRepository;

    @Autowired
    CustDeductRepository custDeductRepository;

    @Autowired
    CustFamilyRepository custFamilyRepository;

    @Autowired
    HelpdeskRepository helpdeskRepository;

    @Autowired
    AuthInfoRepository authInfoRepository;

    @Autowired
    CustTermsAgmtRepository custTermsAgmtRepository;

    @Autowired
    NotiSettingRepository notiSettingRepository;

    @Autowired
    Finger finger;


//    private final UserJpaRepository userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능

    /*
        public Page<UserDto> userSearch(UserVo condition, Pageable pageable){
            System.out.println("222222222");
            return  userJpaRepo.search(condition, pageable);
        }
    */
    public String saveJob(SaveJobVo saveJobVo) {
        CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCid(saveJobVo.getCid());
        if (custInfoDtl == null) {
            custInfoDtl = new CustInfoDtl(saveJobVo);
        } else {
            // 사업 유형 처리
            custInfoDtl.fill(saveJobVo);
        }
        custInfoDtlRepository.save(custInfoDtl);

        if (saveJobVo.getIsNewBusin() != null && saveJobVo.getIsNewBusin().isNo()) {
            CustDeduct custDeduct = custDeductRepository.selectByIds(saveJobVo.getCid(), DateUtil.getThisYear());
            if (custDeduct == null) {
                // 직전년도 수입 처리 (cust_deduct)
                custDeduct = new CustDeduct();
                custDeduct.setCid(saveJobVo.getCid());
                custDeduct.setYear(DateUtil.getThisYear());
            }
            custDeduct.setIncome(Long.parseLong(saveJobVo.getIncome()));
            custDeductRepository.save(custDeduct);
        }

        return Constants.CODE_RET_OK;
    }


    public void saveFamily(SaveFamilyVo saveFamilyVo) {
        List<CustFamily> custFamilies = new ArrayList<>();
        for (SaveFamilyVo.Family family : saveFamilyVo.getFamilies()) {
            CustFamily custFamily = new CustFamily(saveFamilyVo.getCid(), family);

            custFamilies.add(custFamily);
        }

        if (custFamilies.size() > 0) {
            List<CustFamily> families = custFamilyRepository.findAllByCid(saveFamilyVo.getCid());
            custFamilyRepository.deleteAll(families);
            custFamilyRepository.saveAll(custFamilies);
        }
//        return Constants.CODE_RET_OK;
    }


    public void ask(AskVo askVo) {
        Helpdesk helpdesk = new Helpdesk(askVo);
        helpdeskRepository.save(helpdesk);

        // CS담당자에게 이메일 통지
        finger.sendEmail(askVo);

    }

    public Page<AnswerDto> answer(String cid, Pageable pageable) {
        Page<AnswerDto> answerDtos = helpdeskRepository.selectAnswer(cid, pageable);

        return answerDtos;
    }


    public MemberDto infos(String cid) {
        final UserInterface userInterface = authInfoRepository.selectUserByCid(cid);
        if (userInterface == null) {
            throw new UserNotFoundException();
        }

        final CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCid(cid);

        final UserDto userDto = new UserDto(userInterface);
        userDto.setAdditional(custInfoDtl);

        final MemberDto memberDto = new MemberDto();
        memberDto.setUser(userDto);

        return memberDto;
    }


    public DeductDto deduct(String cid, int year) {
        CustDeduct custDeduct = custDeductRepository.selectByIds(cid, year);
        if (custDeduct == null) {
        }

        return new DeductDto(custDeduct);
    }

    public void saveDeduct(DeductVo deductVo) {
        CustDeduct custDeduct = new CustDeduct(deductVo);

        custDeductRepository.save(custDeduct);
    }


    public List<FamilyDto> family(String cid) {
        List<CustFamily> custFamilyList = custFamilyRepository.findAllByCid(cid);
        if (custFamilyList == null) {
        }

        List<FamilyDto> familyDtos = new ArrayList<>();
        custFamilyList.forEach(family -> {
            FamilyDto familyDto = new FamilyDto(family);
            familyDtos.add(familyDto);
        });

        return familyDtos;
    }


    public CustTermsDto terms(String cid) {
        List<CustTermsVo> custTermsVo = custTermsAgmtRepository.selectCustTerms(cid);
        NotiSetting notiSetting = notiSettingRepository.findByCid(cid);
        CustTermsDto custTermsDto = new CustTermsDto(custTermsVo, notiSetting);

        return custTermsDto;
    }

}

/*
        final Page<Faq> faqList = faqRepository.findAll(pageable);
        List<FaqDto> faqDtoList = new ArrayList<>();
        faqList.getContent().forEach(faq -> {
            FaqDto faqDto = new FaqDto();
            faqDto.setFaq(faq);
            faqDtoList.add(faqDto);
        });
 */