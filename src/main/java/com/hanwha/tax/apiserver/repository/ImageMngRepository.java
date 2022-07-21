package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.ImageMng;
import com.hanwha.tax.apiserver.entity.NotiSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageMngRepository extends JpaRepository<ImageMng, Long> {

}
