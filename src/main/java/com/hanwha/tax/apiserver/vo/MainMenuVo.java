package com.hanwha.tax.apiserver.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

public interface MainMenuVo {
    public String getCustGrade();
    public String getId();
    public Integer getOrder();

    public String getTitle();
    public String getType();
    public String getSubType();
    public String getUrl();
    public String getUrlType();
    public String getButtonName();

    public Long getImageId();
    public String getImageUrl();
    public String getBgColor();
    public Integer getHeight();
    public Integer getWidth();

}
