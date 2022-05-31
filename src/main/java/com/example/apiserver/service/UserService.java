package com.example.apiserver.service;

import com.example.apiserver.Constants;
import com.example.apiserver.dto.UserDto;
//import com.example.apiserver.repository.UserJpaRepository;
import com.example.apiserver.vo.SaveJobVo;
import com.example.apiserver.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

//    private final UserJpaRepository userJpaRepo; // Jpa를 활용한 CRUD 쿼리 가능

/*
    public Page<UserDto> userSearch(UserVo condition, Pageable pageable){
        System.out.println("222222222");
        return  userJpaRepo.search(condition, pageable);
    }
*/
    public String saveJob(SaveJobVo saveJobVo) {
        return Constants.CODE_RET_OK;
    }

}
