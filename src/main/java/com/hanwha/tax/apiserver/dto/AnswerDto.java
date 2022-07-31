package com.hanwha.tax.apiserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

public interface AnswerDto {

    String getType();
    String getSubject();
    String getContent();
    String getAnswer();

}
