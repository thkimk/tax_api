package com.hanwha.tax.apiserver.util;

import com.coocon.plugin.crypt.Crypto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hanwha.tax.apiserver.repository.AuthInfoRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;


@Slf4j
public class Utils {
    static String strKey = "sPOuHDI6sA4K49uhA4Mi8eD2xkgoZRPR";
    static String strIv = "c19fY3HUtIXeroka";

    static Crypto crypto = new Crypto();

//    @Autowired
//    static AuthInfoRepository authInfoRepository;


    public static void logCalled(String op, Object object) {
        if (object == null) log.info("## [CALLED] {}() : null", op);
        else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                log.info("## [CALLED] {}() : {}", op, mapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.info("## [CALLED] {}() : {}", op, object.toString());
            }
        }
    }


    public static void logCallReturned(String op, Object object) {
        if (object == null) log.info("## [RETURN] {}() : null", op);
        else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                log.info("## [RETURN] {}() : {}", op, mapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.info("## [RETURN] {}() : {}", op, object.toString());
            }
        }
    }

    public static void logExtCall(String op, Object object) {
        if (object == null) log.info("## [EXT_CALL] {}() : null", op);
        else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                log.info("## [EXT_CALL] {}() : {}", op, mapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.info("## [EXT_CALL] {}() : {}", op, object.toString());
            }
        }
    }

    public static void logExtCallReturned(String op, Object object) {
        if (object == null) log.info("## [EXT_RETURN] {}() : null", op);
        else {
            try {
                ObjectMapper mapper = new ObjectMapper();
                log.info("## [EXT_RETURN] {}() : {}", op, mapper.writeValueAsString(object));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                log.info("## [EXT_RETURN] {}() : {}", op, object.toString());
            }
        }
    }

    public static String cid() {
        return MDC.get("cid");
    }

    public static String osType() {
        String[] uas = devs();
        if (uas == null || uas.length < 3) return "NONE";

        return uas[2];
    }

    /**
     * uas : [0]APPversion; [1]DEVname; [2]OStype; [3]OSversion
     * @return
     */
    public static String[] devs() {
        String ua = MDC.get("ua");
        if (ua == null) return null;

        return ua.split(";");
    }


    public static String random10value() {
        RandomString rs = new RandomString(10);
        return rs.nextString();
    }

    public static String encCoocon(String plain) {
//        String plain = "abcdefghijklmnopqrstuvwxyz123456";
//        String strKey = "12345678901234567890123456789012";
//        String strIv = "1234567890123456";

//        Crypto crypto = new Crypto();
        String enc = "";
        try {
            crypto.setKey(strKey);
            crypto.setIv(strIv);
            crypto.setAlg("AES/CBC/PKCS5Padding");

            enc = crypto.encrypt(plain);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return enc;
//        String aesDec = crypto.decrypt(aesEnc);
    }

/*    public static String ci(AuthInfoRepository authInfoRepository) {
//        return "Gg3GIzkmziVhqfx8IOSFItnLjUP49iIM";

//        String custId = custId();
//        String cid = authInfoRepository.getCiByCustId(custId);
        return authInfoRepository.getCiByCustId(custId());
    }*/


    public static String yyyymmddYester() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static String yyyymmdd() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
    }

    public static int realAge(String birth) {
        LocalDate now = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));

        int realAge = now.minusYears(parsedBirthDate.getYear()).getYear();
        if (parsedBirthDate.plusYears(realAge).isAfter(now)) {
            realAge = realAge -1;
        }

        return realAge;
    }

    public static int koreaAge(String birth) {
        LocalDate now = LocalDate.now();
        LocalDate parsedBirthDate = LocalDate.parse(birth, DateTimeFormatter.ofPattern("yyyyMMdd"));

        return now.minusYears(parsedBirthDate.getYear()).getYear();
    }

}
