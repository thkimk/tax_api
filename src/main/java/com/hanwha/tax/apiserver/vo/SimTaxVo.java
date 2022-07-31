package com.hanwha.tax.apiserver.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SimTaxVo {
    String jobCode;
    Long preIncome;
    Long income;
    Long outgoing;

    DetailsVo details;

    @Data
    @NoArgsConstructor
    public static class DetailsVo {
        String birth;
        Character gender;

        Character isDisorder;
        Character isHshld;
        Character isNewBusin;
        Character isMarriage;
        Character isSinParent;

        Long npcAmount;
        Long rspAmount;
        Long iraAmount;
        Long medAmount;
        Long sedAmount;

        List<FamilyVo> familys;

        @Data
        @NoArgsConstructor
        public static class FamilyVo {
            String family;
            String birth;
            Character isDisorder;
        }
    }



}
