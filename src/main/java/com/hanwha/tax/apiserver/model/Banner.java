package com.hanwha.tax.apiserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.hanwha.tax.apiserver.entity.MainMenuNoti;
import com.hanwha.tax.apiserver.repository.MainMenuNotiRepository;
import com.hanwha.tax.apiserver.vo.MainMenuVo;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Banner<T extends Banner.BannerBase> {
    String id;
    int order;
    String type;

    T data;


    public Banner(MainMenuVo mainMenuVo, T data) {
        id = mainMenuVo.getId();
        order = mainMenuVo.getOrder();
        type = mainMenuVo.getType();

        this.data = data;
//        this.data.fillData(mainMenuVo);
    }

    public void fillData(MainMenuVo mainMenuVo) {
        data.fillData(mainMenuVo);

    }

    public static class BannerBase {
        protected void fillData(MainMenuVo mainMenuVo) {}
    }

    @Data
    public static class Banner00 extends BannerBase {
        String title;
        String subCopy;
        String url;
        String urlType;
        String imageUrl;
        String buttonName;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = mainMenuVo.getTitle();
            subCopy = mainMenuVo.getSubType();
            url = mainMenuVo.getUrl();
            urlType = mainMenuVo.getUrlType();
            imageUrl = mainMenuVo.getImageUrl();
            buttonName = mainMenuVo.getButtonName();

        }
    }

    /**
     * 다이나믹 배너 : Notice
     */
    @Data
    public static class Banner21 extends BannerBase {
        String title;
        String content;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "알림";
            content = "알림 내용 어쩌고";
        }
    }

    /**
     * 다이나믹 배너 : Income
     */
    @Data
    public static class Banner22 extends BannerBase {
        String title;
        String comment;
        String content;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "6월 수입은 어땠을까요?";
            comment = "놓친 내역은 없는지 꼼꼼히 확인해 보세요";
            content = "6월 총 수입액 / 200,000,000 원";
        }

    }

    /**
     * 다이나믹 배너 : Income + Image
     */
    @Data
    public static class Banner23 extends BannerBase {
        String title;
        String comment;
        String content;
        String image;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "6월 수입은 어땠을까요?";
            comment = "놓친 내역은 없는지 꼼꼼히 확인해 보세요";
            content = "6월 총 수입액 / 200,000,000 원";
            image = "http://localhost";
        }

    }

    /**
     * 다이나믹 배너 : Outgoing
     */
    @Data
    public static class Banner24 extends BannerBase {
        String title;
        String comment;
        String content;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "6월 필요경비는 어땠을까요?";
            comment = "놓친 내역은 없는지 꼼꼼히 확인해 보세요";
            content = "6월 총 지출액 / 90,000,000 원";
        }
    }

    /**
     * 다이나믹 배너 : Outgoing + Image
     */
    @Data
    public static class Banner25 extends BannerBase {
        String title;
        String comment;
        String content;
        String image;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "6월 필요경비는 어땠을까요?";
            comment = "놓친 내역은 없는지 꼼꼼히 확인해 보세요";
            content = "6월 총 지출액 / 90,000,000 원";
            image = "http://localhost";
        }
    }

    /**
     * 다이나믹 배너 : Reward
     */
    @Data
    public static class Banner26 extends BannerBase {
        String title;
        String period;
        String count;
        String amount;

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
            title = "이번달 Tax App으로 아낀 세금은?";
            period = "2022.01.01 ~ 2022.07.31";
            count = "/100 건";
            amount = "/520,000 원";
        }
    }

    /**
     * 다이나믹 배너 : Graph
     */
    @Data
    public static class Banner27 extends BannerBase {
    }

    /**
     * 개인화 배너
     */
    @Data
    public static class Banner03 extends BannerBase {
        List<String> notis = new ArrayList<>();

        @Override
        public void fillData(MainMenuVo mainMenuVo) {
//            List<MainMenuNoti> mainMenuNotis = Banner.mainMenuNotiRepository.findAll();
//            for (MainMenuNoti mainMenuNoti : mainMenuNotis) {
//                notis.add(mainMenuNoti.getContent());
//            }
            notis.add("noti001 입니다.");
            notis.add("noti002 입니다.");
        }
    }

}

