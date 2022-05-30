package com.example.apiserver.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AppInitsDto {
    List<AppInfoDto> app_infos = new ArrayList<>();
    List<NotiMsgDto> noti_msgs = new ArrayList<>();
}
