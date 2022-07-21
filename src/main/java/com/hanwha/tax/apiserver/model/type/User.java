package com.hanwha.tax.apiserver.model.type;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

public class User {

    @Getter
    @AllArgsConstructor
    public enum Gender {
        @JsonProperty("M")
        MALE,
        @JsonProperty("F")
        FEMALE;

        public Character toCharacter() {
            return this == MALE ? 'M' : 'F';
        }

        public static Gender parse(Character gender) {
            return gender == 'M' ? MALE : FEMALE;
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Telecom {
        @JsonProperty("-1")
        NONE("-1"),
        @JsonProperty("01")
        SKT("01"),
        @JsonProperty("02")
        KT("02"),
        @JsonProperty("03")
        LGU("03"),
        @JsonProperty("04")
        CHEAP_SKT("04"),
        @JsonProperty("05")
        CHEAP_KT("05"),
        @JsonProperty("06")
        CHEAP_LGU("06");

        private final String code;

        public static Telecom parse(String code) {
            for (Telecom telecom : values()) {
                if (telecom.code.equals(code)) {
                    return telecom;
                }
            }

            return Telecom.NONE;
        }
    }
}
