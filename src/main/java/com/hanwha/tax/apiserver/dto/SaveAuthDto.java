package com.hanwha.tax.apiserver.dto;


import lombok.Data;

@Data
public class SaveAuthDto {
    String cid;
    Character isSucc;
    String failMsg;

}
