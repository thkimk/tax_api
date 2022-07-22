package com.hanwha.tax.apiserver.dto.list;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.hanwha.tax.apiserver.model.type.ListType;

public abstract class ListItem {

    @JsonProperty("viewType")
    private final ListType listType = viewType();

    public abstract ListType viewType();
}
