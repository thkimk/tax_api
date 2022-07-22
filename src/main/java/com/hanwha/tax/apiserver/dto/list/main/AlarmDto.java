package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class AlarmDto extends ListItem {

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


    @Override
    public ListType viewType() {
        return ListType.EVENT;
    }

    public AlarmDto(MainBannerInterface item) {
        flag = item.getFlag();
        title = item.getTitle();
        subTitle = item.getSubTitle();
        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
    }
}
