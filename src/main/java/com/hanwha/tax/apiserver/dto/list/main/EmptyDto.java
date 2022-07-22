package com.hanwha.tax.apiserver.dto.list.main;

import com.hanwha.tax.apiserver.dto.list.ListItem;
import com.hanwha.tax.apiserver.model.type.ListType;

public class EmptyDto extends ListItem {

    @Override
    public ListType viewType() {
        return ListType.NONE;
    }
}
