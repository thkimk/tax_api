package com.example.apiserver;

public class Constants {
    public final static String API = "/api";
    public final static String VERSION = "v1";

    public final static String CODE_RET_OK = "OK";
    public final static String CODE_RET_NOK = "NOK";

    // 고객 상태코드 : 정상(00), 탈퇴(02), 휴면(03)
    public final static String CUST_ST_NORMAL = "00";
    public final static String CUST_ST_SIGNOUT = "02";
    public final static String CUST_ST_REST = "03";

    // 고객 등급코드
    public final static String CUST_GR_ASOC = "00";
    public final static String CUST_GR_REGL = "01";
}
