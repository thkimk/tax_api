package com.example.apiserver.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class SaveFamilyVo {
    String custId;
    List<Family> families = new ArrayList<>();

    @Data
    public static class Family {
        String family;
        String birth;
        char isDisorder;

    }
}
