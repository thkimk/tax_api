package com.hanwha.tax.apiserver.dto;


public interface MainBannerInterface extends ImageInterface {
    String getBannerId();

    String getType();

    String getFlag();

    String getTitle();

    String getSubTitle();

    String getUrlType();

    String getUrl();

    String getButtonName();
}
