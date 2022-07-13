package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MainMenuDto {

    String custGrade;
    List<Banner> banners = new ArrayList<>();

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Banner {
        String id;
        int order;
        String type;

        String title;
        String subCopy;
        String url;
        String urlType;
        String imageUrl;
        String buttonName;

        public Banner(MainMenuVo mainMenuVo) {
            id = mainMenuVo.getId();
            order = mainMenuVo.getOrder();

            type = mainMenuVo.getType();
            subCopy = mainMenuVo.getSubType();
            url = mainMenuVo.getUrl();
            urlType = mainMenuVo.getUrlType();
            imageUrl = mainMenuVo.getImageUrl();
            buttonName = mainMenuVo.getButtonName();
        }
    }


    public MainMenuDto(List<MainMenuVo> mainMenuVos) {
        custGrade = mainMenuVos.get(0).getCustGrade();

        for (MainMenuVo mainMenuVo : mainMenuVos) {
            Banner banner = new Banner(mainMenuVo);

            banners.add(banner);
        }
    }

    public void procDynamicBanner() {
        for (Banner banner : banners) {
            if (Integer.parseInt(banner.getType()) > 20) {
                System.out.println("## procDynamicBanner(): "+ banner.getType());
            }
        }
    }

}
