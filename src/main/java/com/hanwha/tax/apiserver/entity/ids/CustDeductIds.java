package com.hanwha.tax.apiserver.entity.ids;

import lombok.NoArgsConstructor;

import java.io.Serializable;


@NoArgsConstructor
public class CustDeductIds implements Serializable {

    private String cid;
    private int year;
}
