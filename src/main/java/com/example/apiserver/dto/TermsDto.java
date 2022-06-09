package com.example.apiserver.dto;


import lombok.Data;

import javax.persistence.Column;

@Data
public class TermsDto {
    private long id;
    private String termsName;
    private String termsVersion;
    private String termsType;
    private char viewYn;
    private String termsStmt;
}
