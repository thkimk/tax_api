package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.MainMenu;
import com.hanwha.tax.apiserver.entity.MainMenuNoti;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MainMenuNotiRepository extends JpaRepository<MainMenuNoti, Long> {

}


