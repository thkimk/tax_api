package com.hanwha.tax.apiserver.config.security;

//import com.hanwha.tax.apiserver.repository.UserJpaRepository;
import com.hanwha.tax.apiserver.repository.CustInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private final CustInfoRepository custInfoRepository;

    public CustomUserDetails loadUserByUsername(String custId) {
        CustomUserDetails tempUserDetails = new CustomUserDetails();
        tempUserDetails.setUsername(custId);
        tempUserDetails.setPassword("1234");
        return tempUserDetails;
//        return userJpaRepo.findById(Long.valueOf(userPk)).orElseThrow(UserNotFoundException::new);
    }
}