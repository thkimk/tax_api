package com.hanwha.tax.apiserver.model.type;

import io.swagger.models.auth.In;
import lombok.Data;

@Data
public class Image {
    private Long id;
    private String url;
    private String bgColor;
    private Integer height;
    private Integer width;
}
