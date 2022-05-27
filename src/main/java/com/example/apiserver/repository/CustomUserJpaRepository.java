package com.example.apiserver.repository;

import com.example.apiserver.dto.UserDto;
import com.example.apiserver.vo.UserVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface CustomUserJpaRepository {

   Page<UserDto> search(UserVo condition, Pageable pageable);
}
