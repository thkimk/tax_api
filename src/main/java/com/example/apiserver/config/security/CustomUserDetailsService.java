package com.example.apiserver.config.security;

import com.example.apiserver.advice.exception.UserNotFoundException;
//import com.example.apiserver.repository.UserJpaRepository;
import com.example.apiserver.vo.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

/*
    private final UserJpaRepository userJpaRepo;
*/

    public CustomUserDetails loadUserByUsername(String userPk) {
        CustomUserDetails tempUserDetails = new CustomUserDetails();
        // 유저 테이블 없는 관계로 받은 ID 그대로 넘겨줌
        tempUserDetails.setUserName(userPk);
        tempUserDetails.setEmail("aaa");
        tempUserDetails.setPassword("bbb");
        tempUserDetails.setName(userPk);
        return tempUserDetails;

//        return null;
//        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(UserNotFoundException::new);
    }
}