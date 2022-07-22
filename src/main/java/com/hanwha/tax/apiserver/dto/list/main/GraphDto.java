package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class GraphDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("urlType")
    private String urlType;
    @JsonProperty("url")
    private String url;
    @JsonProperty("buttonName")
    private String buttonName;
    @JsonProperty("image")
    private ImageDto image;


    @Override
    public ListType viewType() {
        return ListType.DYNAMIC_GRAPH;
    }

    public GraphDto(MainBannerInterface item) {
        id = item.getBannerId();
        flag = item.getFlag();
        title = item.getTitle();
        subTitle = item.getSubTitle();
        urlType = item.getUrlType();
        url = item.getUrl();
        buttonName = item.getButtonName();
        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
    }
}
