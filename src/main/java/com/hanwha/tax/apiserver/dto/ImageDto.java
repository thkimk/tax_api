package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ImageDto {
    @JsonProperty("url")
    private String url;

    @JsonProperty("bgColor")
    private String bgColor;

    @JsonProperty("repeatCount")
    private int repeatCount;

    @JsonProperty("width")
    private int width = 0;

    @JsonProperty("height")
    private int height = 0;

    public ImageDto(ImageInterface item) {
        url = item.getImageUrl();
        bgColor = item.getBgColor();
        repeatCount = item.getAniCnt();
        width = item.getWidth();
        height = item.getHeight();
    }
}
