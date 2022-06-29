package com.example.apiserver.vo;


import lombok.Data;

@Data
public class IdenOtpConfirmVo {
    String txSeqNo; // OTP요청 후, 받은 응답값
    String telNo;   // 폰 번호
    String otpNo;   // OTP값

}
