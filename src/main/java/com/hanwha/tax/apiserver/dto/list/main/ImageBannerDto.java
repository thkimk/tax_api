package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class ImageBannerDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("urlType")
    private String urlType;
    @JsonProperty("url")
    private String url;
    @JsonProperty("image")
    private ImageDto image;


    @Override
    public ListType viewType() {
        return ListType.IMAGE_BANNER;
    }

    public ImageBannerDto(MainBannerInterface item) {
        id = item.getBannerId();
        title = item.getTitle();
        subTitle = item.getSubTitle();
        urlType = item.getUrlType();
        url = item.getUrl();
        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
    }
}
