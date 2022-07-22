package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ButtonDto {
    @JsonProperty("type")
    private String type;
    @JsonProperty("url")
    private String url;
    @JsonProperty("name")
    private String name;

    public ButtonDto(MainBannerInterface item) {
        type = item.getUrlType();
        url = item.getUrl();
        name = item.getButtonName();
    }
}
