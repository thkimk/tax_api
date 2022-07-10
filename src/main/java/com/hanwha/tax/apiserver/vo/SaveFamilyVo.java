package com.hanwha.tax.apiserver.vo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class SaveFamilyVo {
    String cid;
    List<Family> families = new ArrayList<>();

    @Data
    public static class Family {
        String family;
        String birth;
        Character isDisorder;

    }
}
