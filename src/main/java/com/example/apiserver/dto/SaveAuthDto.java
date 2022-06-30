package com.example.apiserver.dto;


import lombok.Data;

@Data
public class SaveAuthDto {
    String custId;
    Character isSucc;
    String failMsg;

}
