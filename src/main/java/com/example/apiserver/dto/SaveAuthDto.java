package com.example.apiserver.dto;


import lombok.Data;

@Data
public class SaveAuthDto {
    String custId;
    char isSucc;
    String failMsg;

}
