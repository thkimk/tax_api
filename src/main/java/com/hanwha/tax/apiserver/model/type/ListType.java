package com.hanwha.tax.apiserver.model.type;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ListType {
    @JsonProperty("00")
    VISUAL("00"),
    @JsonProperty("01")
    WELCOME("01"),
    @JsonProperty("02")
    ALARM("02"),
    @JsonProperty("03")
    PERSONAL("03"),
    @JsonProperty("11")
    CONTENTS("11"),
    @JsonProperty("12")
    EVENT("12"),
    @JsonProperty("13")
    IMAGE_BANNER("13"),
    @JsonProperty("21")
    DYNAMIC_NOTICE("21"),
    @JsonProperty("22")
    DYNAMIC_INCOME("22"),
    @JsonProperty("23")
    DYNAMIC_OUTGOING("23"),
    @JsonProperty("26")
    DYNAMIC_REWARD("26"),
    @JsonProperty("27")
    DYNAMIC_GRAPH("27"),
    @JsonProperty("-1")
    NONE("-1"),;
    private final String type;

    public static ListType parse(String type) {
        for (ListType listType : values()) {
            if (listType.type.equals(type)) {
                return listType;
            }
        }
        return NONE;
    }
}
