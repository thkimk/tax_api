package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.Constants;
//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.entity.CustDeduct;
import com.hanwha.tax.apiserver.entity.CustDeductIds;
import com.hanwha.tax.apiserver.entity.CustFamily;
import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.repository.CustDeductRepository;
import com.hanwha.tax.apiserver.repository.CustFamilyRepository;
import com.hanwha.tax.apiserver.repository.CustInfoDtlRepository;
import com.hanwha.tax.apiserver.util.DateUtil;
import com.hanwha.tax.apiserver.vo.SaveFamilyVo;
import com.hanwha.tax.apiserver.vo.SaveJobVo;
import lombok.RequiredArgsConstructor;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustService {
    @Autowired
    CustInfoDtlRepository custInfoDtlRepository;

    @Autowired
    CustDeductRepository custDeductRepository;

    @Autowired
    CustFamilyRepository custFamilyRepository;

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
            custFamilyRepository.saveAll(custFamilies);
        }
//        return Constants.CODE_RET_OK;
    }

}
