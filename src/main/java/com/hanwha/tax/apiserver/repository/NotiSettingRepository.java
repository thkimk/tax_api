package com.hanwha.tax.apiserver.repository;

import com.hanwha.tax.apiserver.entity.CustInfoDtl;
import com.hanwha.tax.apiserver.entity.CustTermsAgmt;
import com.hanwha.tax.apiserver.entity.NotiSetting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotiSettingRepository extends JpaRepository<NotiSetting, Long> {
//    NotiSetting findByCustId(String custId);
    NotiSetting findByCid(String cid);
}
