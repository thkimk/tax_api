package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.model.Banner;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class MainMenuDto {

    String custGrade;
    List<Banner> banners = new ArrayList<>();

    public MainMenuDto(List<MainMenuVo> mainMenuVos) {
        custGrade = mainMenuVos.get(0).getCustGrade();

        for (MainMenuVo mainMenuVo : mainMenuVos) {
            Banner banner = newBanner(mainMenuVo);
            banner.fillData(mainMenuVo);

            banners.add(banner);
        }
    }

    public Banner newBanner(MainMenuVo mainMenuVo) {
        switch (mainMenuVo.getType()) {
            case "21":  return new Banner<Banner.Banner21>(mainMenuVo, new Banner.Banner21());
            case "22":  return new Banner<Banner.Banner22>(mainMenuVo, new Banner.Banner22());
            case "23":  return new Banner<Banner.Banner23>(mainMenuVo, new Banner.Banner23());
            case "24":  return new Banner<Banner.Banner24>(mainMenuVo, new Banner.Banner24());
            case "25":  return new Banner<Banner.Banner25>(mainMenuVo, new Banner.Banner25());
            case "26":  return new Banner<Banner.Banner26>(mainMenuVo, new Banner.Banner26());
            case "27":  return new Banner<Banner.Banner27>(mainMenuVo, new Banner.Banner27());
            case "03":  return new Banner<Banner.Banner03>(mainMenuVo, new Banner.Banner03());

            default:    return new Banner<Banner.Banner00>(mainMenuVo, new Banner.Banner00());
        }
    }




}
