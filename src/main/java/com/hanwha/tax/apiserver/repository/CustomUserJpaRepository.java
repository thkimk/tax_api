package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.dto.UserDto;
import com.hanwha.tax.apiserver.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomUserJpaRepository {

   Page<UserDto> search(UserVo condition, Pageable pageable);
}
