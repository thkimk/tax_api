package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class DynamicIncomeDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("content")
    private String content;
    @JsonProperty("buttonName")
    private String buttonName;
    @JsonProperty("image")
    private ImageDto image;


    @Override
    public ListType viewType() {
        return ListType.DYNAMIC_INCOME;
    }

    public DynamicIncomeDto(MainBannerInterface item) {
        id = item.getBannerId();
        title = item.getTitle();
        subTitle = item.getSubTitle();
        buttonName = item.getButtonName();
        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
    }
}
