package com.cy.utils.common;

import android.annotation.SuppressLint;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
    private DateUtil() {
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 时分秒 日期
     */
    private static final DateFormat sFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm E");
    /**
     * 时分秒
     */
    private static final DateFormat sFormat1 = new SimpleDateFormat("HH:mm:ss");
    /**
     * 年月日 时分秒
     */
    private static final DateFormat sFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static long getCurrentData() {
        return System.currentTimeMillis();
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurformatData() {
        Calendar currentDate = Calendar.getInstance();
        String str = sFormat.format(currentDate.getTime());
        return str;
    }

    @SuppressLint("SimpleDateFormat")
    public static String getCurformatDataNoWeek() {

        Calendar currentDate = Calendar.getInstance();
        String str = (new SimpleDateFormat("yyyyMMdd")).format(currentDate.getTime());
        return str;
    }

    public static long getDataBeginTime() {

        Calendar currentDate = Calendar.getInstance();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        return currentDate.getTimeInMillis();

    }

    // 把日期转为字符串
    @SuppressLint("SimpleDateFormat")
    public static String converToString(Date date) {
        return sFormat2.format(date);
    }

    // 把字符串转为日期
    public static Date converToDate(String strDate) throws Exception {
        return sFormat2.parse(strDate);
    }

    // 把Long型转为日期 时 分 秒
    public static String converToString(Long date) {
        return sFormat1.format(date);
    }

    public static String converToStringWithFormatter(Date date, String formatter) {
        DateFormat df = new SimpleDateFormat(formatter);
        return df.format(date);
    }

    /*
     * 毫秒的日期转为日期类型
     */
    public static String milliDate2Day(long date) {
        Date dat = new Date(date);
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(dat);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String day = format.format(gc.getTime());
        return day;

    }

    /**
     * 格式化毫秒数为 xx:xx:xx这样的时间格式。
     *
     * @param ms 毫秒数
     * @return 格式化后的字符串
     */
    public static String formatMs(long ms) {
        int seconds = (int) (ms / 1000);
        int finalSec = seconds % 60;
        int finalMin = seconds / 60 % 60;
        int finalHour = seconds / 3600;

        StringBuilder msBuilder = new StringBuilder("");
        if (finalHour > 9) {
            msBuilder.append(finalHour).append(":");
        } else if (finalHour > 0) {
            msBuilder.append("0").append(finalHour).append(":");
        }

        if (finalMin > 9) {
            msBuilder.append(finalMin).append(":");
        } else if (finalMin > 0) {
            msBuilder.append("0").append(finalMin).append(":");
        } else {
            msBuilder.append("00").append(":");
        }

        if (finalSec > 9) {
            msBuilder.append(finalSec);
        } else if (finalSec > 0) {
            msBuilder.append("0").append(finalSec);
        } else {
            msBuilder.append("00");
        }

        return msBuilder.toString();
    }

}
