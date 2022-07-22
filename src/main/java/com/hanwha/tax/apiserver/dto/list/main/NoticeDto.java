package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class NoticeDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("content")
    private String content;
    @JsonProperty("urlType")
    private String urlType;
    @JsonProperty("url")
    private String url;


    @Override
    public ListType viewType() {
        return ListType.DYNAMIC_NOTICE;
    }

    public NoticeDto(MainBannerInterface item) {
        id = item.getBannerId();
        title = "알림";
        content = item.getSubTitle();
        urlType = item.getUrlType();
        url = item.getUrl();
    }
}
