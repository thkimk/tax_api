package com.example.apiserver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Utils {
    static int randCustId = 0;


    public static String genCustId() {
        String yymm = LocalDate.now().format(DateTimeFormatter.ofPattern("YYMM"));
        String rand = String.format("%05d", randCustId++);
        return yymm.concat(rand);
    }

}
