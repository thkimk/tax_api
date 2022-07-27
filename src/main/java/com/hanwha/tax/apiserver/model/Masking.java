package com.hanwha.tax.apiserver.model;


import com.hanwha.tax.apiserver.entity.CustInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Data
@Slf4j
public class Masking {
    String mName;
    String mEmail;
    String mMobile;


    CustInfo custInfo = null;

    public void init(CustInfo custInfo) {
        this.custInfo = custInfo;

    }

    public String maskName(String name) {
        String regex = "(^[가-힣]+)$";

        Matcher matcher = Pattern.compile(regex).matcher(name);
        if (matcher.find()) {
            int length = name.length();

            String middleMask = "";
            if (length > 2) {
                middleMask = name.substring(1, length - 1);
            } else {	// 이름이 외자
                middleMask = name.substring(1, length);
            }

            String dot = "";
            for (int i = 0; i<middleMask.length(); i++) {
                dot += "*";
            }

            if (length > 2) {
                return name.substring(0, 1)
                        + middleMask.replace(middleMask, dot)
                        + name.substring(length-1, length);
            } else { // 이름이 외자 마스킹 리턴
                return name.substring(0, 1)
                        + middleMask.replace(middleMask, dot);
            }
        }
        return name;
    }

    public String maskMobile(String phoneNo) {
        String regex = "(\\d{2,3})-?(\\d{3,4})-?(\\d{4})$";

        Matcher matcher = Pattern.compile(regex).matcher(phoneNo);
        if (matcher.find()) {
            String target = matcher.group(2);
            int length = target.length();
            char[] c = new char[length];
            Arrays.fill(c, '*');

            return phoneNo.replace(target, String.valueOf(c));
        }
        return phoneNo;
    }

    public String maskEmail(String email) {
        String regex = "\\b(\\s+)+@(\\s+.\\s+)";

        Matcher matcher = Pattern.compile(regex).matcher(email);
        if (matcher.find()) {
            String target = matcher.group(1);
            int length = target.length();
            if (length > 3) {
                char[] c = new char[length - 3];
                Arrays.fill(c, '*');

                return email.replace(target, target.substring(0, 3) + String.valueOf(c));
            }
        }
        return email;
    }

/* 생년월일도 마스킹 필요??
    public String maskBirth(String birth) throws Exception {
        String regex = "^((19|20)\\d\\d)?([-/.])?(0[1-9]|1[012])([-/.])?(0[1-9]|[12][0-9]|3[01])$";

        Matcher matcher = Pattern.compile(regex).matcher(birth);
        if(matcher.find()) {
            return birth.replace("[0-9]", "*");
        }
        return birth;
    }
*/

}
