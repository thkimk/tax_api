package com.example.apiserver.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppInitsDto {
    List<AppInfoDto> appInfos = new ArrayList<>();
    List<NotiMsgDto> notiMsgs = new ArrayList<>();
    boolean isMember = false;
}
