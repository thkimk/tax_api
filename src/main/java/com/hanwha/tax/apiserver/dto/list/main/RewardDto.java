package com.hanwha.tax.apiserver.dto.list.main;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.dto.ButtonDto;
import com.hanwha.tax.apiserver.dto.ImageDto;
import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;
import lombok.Setter;

@Setter
public class RewardDto extends ListItem {

    @JsonProperty("id")
    private String id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("subTitle")
    private String subTitle;

    @JsonProperty("count")
    private String count;
    @JsonProperty("amount")
    private String amount;

    @JsonProperty("image")
    private ImageDto image;
    @JsonProperty("button")
    private ButtonDto button;


    @Override
    public ListType viewType() {
        return ListType.DYNAMIC_REWARD;
    }

    public RewardDto(MainBannerInterface item) {
        id = item.getBannerId();
        title = item.getTitle();
        subTitle = item.getSubTitle();

        if (item.getImageUrl() != null) {
            image = new ImageDto(item);
        }
        if (item.getButtonName() != null) {
            button = new ButtonDto(item);
        }
    }
}
