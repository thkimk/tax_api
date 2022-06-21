package com.example.apiserver;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@Slf4j
public class Utils {
    static int randCustId = 0;


    public static String genCustId() {
        String yymm = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMM"));
        String rand = String.format("%05d", randCustId++);
        return yymm.concat(rand);
    }

    public static void logCalled(String op, Object object) {
        if (object == null) log.info("## [CALLED] {}() : null", op);
        else log.info("## [CALLED] {}() : {}", op, object.toString());
    }


    public static void logCallReturned(String op, Object object) {
        if (object == null) log.info("## [RETURN] {}() : null", op);
        else log.info("## [RETURN] {}() : {}", op, object.toString());
    }


}
