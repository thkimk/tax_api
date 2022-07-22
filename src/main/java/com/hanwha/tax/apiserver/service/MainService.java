package com.hanwha.tax.apiserver.service;

import com.hanwha.tax.apiserver.dto.MainBannerInterface;
import com.hanwha.tax.apiserver.dto.PageableDto;
import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.dto.list.main.*;
import com.hanwha.tax.apiserver.model.type.ListType;
import com.hanwha.tax.apiserver.repository.CustRepository;
import com.hanwha.tax.apiserver.repository.MainMenuRepository;
import com.hanwha.tax.apiserver.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MainService {

    @Autowired
    CustRepository custRepository;

    @Autowired
    MainMenuRepository mainMenuRepository;

    public PageableDto<ListItem> getMainList(Pageable pageable) {
        final String custGrade = custRepository.selectCustGrade(Utils.cid());
        final Page<MainBannerInterface> mainBannerList = mainMenuRepository.selectAllMainBanner(custGrade, pageable);

        return new PageableDto<>(convertListItem(mainBannerList.getContent()), mainBannerList);
    }

    private List<ListItem> convertListItem(List<MainBannerInterface> itemList) {
        final List<ListItem> listItems = new ArrayList<>();

        itemList.forEach(item -> listItems.add(convertItem(item)));
        return listItems;
    }

    private ListItem convertItem(MainBannerInterface item) {
        switch (ListType.parse(item.getType())) {
            case VISUAL:
                return new VisualDto(item);
            case WELCOME:
                return new WelcomeDto(item);
            case ALARM:
                return new AlarmDto(item);
            case PERSONAL:
                return new PersonalDto(item);
            case CONTENTS:
                return new ContentDto(item);
            case EVENT:
                return new EventDto(item);
            case IMAGE_BANNER:
                return new ImageBannerDto(item);
            case DYNAMIC_NOTICE:
                return new NoticeDto(item);
            case DYNAMIC_INCOME:
                return new DynamicIncomeDto(item);
            case DYNAMIC_OUTGOING:
                return new DynamicOutgoingDto(item);
            case DYNAMIC_REWARD:
                return new RewardDto(item);
            case DYNAMIC_GRAPH:
                return new GraphDto(item);
            default:
                return new EmptyDto();
        }
    }

}
