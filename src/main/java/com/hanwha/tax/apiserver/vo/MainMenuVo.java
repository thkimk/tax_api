package com.hanwha.tax.apiserver.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

public interface MainMenuVo {
    public String getCustGrade();
    public String getId();
    public Integer getOrder();

    public String getType();
    public String getSubType();
    public String getUrl();
    public String getUrlType();
    public String getImageUrl();
    public String getButtonName();

}
