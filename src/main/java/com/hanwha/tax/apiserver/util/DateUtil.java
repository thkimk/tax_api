package com.hanwha.tax.apiserver.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {
    private static final SimpleDateFormat yy = new SimpleDateFormat("yy");
    private static final SimpleDateFormat yyyy = new SimpleDateFormat("yyyy");
    private static final SimpleDateFormat yyMMdd = new SimpleDateFormat("yyMMdd");
    private static final SimpleDateFormat yymmdd = new SimpleDateFormat("yymmdd");


   public static Date getTodayDate() {
        return getTodayCalendar().getTime();
    }

    public static Calendar getTodayCalendar() {
        Calendar calendar = Calendar.getInstance(Locale.KOREAN);
        calendar.setTime(new Date());
        return calendar;
    }

    public static String getThisYearString() {
        return yyyy.format(new Date());
    }

    public static int getThisYear() {
        return Integer.parseInt(getThisYearString());
    }
}
