package com.example.apiserver.service;

import com.example.apiserver.Constants;
//import com.example.apiserver.repository.UserJpaRepository;
import com.example.apiserver.entity.CustInfo;
import com.example.apiserver.entity.CustInfoDtl;
import com.example.apiserver.repository.CustInfoDtlRepository;
import com.example.apiserver.vo.SaveJobVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustService {
    @Autowired
    CustInfoDtlRepository custInfoDtlRepository;

//    private final UserJpaRepository userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능

    /*
        public Page<UserDto> userSearch(UserVo condition, Pageable pageable){
            System.out.println("222222222");
            return  userJpaRepo.search(condition, pageable);
        }
    */
    public String saveJob(SaveJobVo saveJobVo) {
        CustInfoDtl custInfoDtl = custInfoDtlRepository.findByCustId(saveJobVo.getCustId());
        if (custInfoDtl == null) {
            return Constants.CODE_RET_OK;
        }

        custInfoDtl.fill(saveJobVo);
        custInfoDtlRepository.save(custInfoDtl);

        return Constants.CODE_RET_OK;
    }


}
