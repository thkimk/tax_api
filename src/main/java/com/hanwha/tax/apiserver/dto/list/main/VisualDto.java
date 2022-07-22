package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ButtonDto;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class VisualDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("flag")
    private String flag;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;
    @JsonProperty("image")
    private ImageDto image;
    @JsonProperty("button")
    private ButtonDto button;

    @Override
    public ListType viewType() {
        return ListType.VISUAL;
    }

    public VisualDto(MainBannerInterface item) {
        id = item.getBannerId();
        flag = item.getFlag();
        title = item.getTitle();
        subTitle = item.getSubTitle();
        if (item.getButtonName() != null) {
            button = new ButtonDto(item);
        }
        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
    }
}
