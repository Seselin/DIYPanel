package com.seselin.diypanel.util;

import android.text.TextUtils;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Seselin on 2018/5/9 15:56.
 */
public class DateUtils {

    /**
     * 将短时间格式字符串转换为时间 yyyy-MM-dd
     *
     * @param strDate
     * @return
     */
    public static Date strToDate(String strDate) {
        String pattern = "yyyy-MM-dd";
        if (strDate.contains(":")) {
            pattern = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        ParsePosition position = new ParsePosition(0);
        Date date = formatter.parse(strDate, position);
        return date;
    }

    public static long strToLong(String strDate) {
        return strToDate(strDate).getTime();
    }

    public static long timeStrToLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition position = new ParsePosition(0);
        Date date = formatter.parse(strDate, position);
        return date.getTime();
    }

    public static boolean checkTime(String date1, String date2) {
        return strToLong(date1) <= strToLong(date2);
    }

    /**
     * @return 获取当前日期 例：2016-04-21
     */
    public static String getCurrentDate() {
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.CHINA);
        return dateFormat.format(date);
    }

    public static String formatDate(int date) {
        String dateStr = String.format("%d", date);
        double value = 0;
        if (!TextUtils.isEmpty(dateStr)) {
            try {
                value = Double.valueOf(dateStr);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        DecimalFormat decimalFormat = new DecimalFormat("#00");// 格式化设置
        return decimalFormat.format(value);
    }

    /**
     * 获取某个月的月末日期
     *
     * @param input 2015-2
     * @return 月末日期 2015-2-01
     */
    public static String getMonthStartDate(String input) {
        return input + "-01";
    }

    /**
     * 获取某个月的月末日期
     *
     * @param input 2015-2
     * @return 月末日期 2015-2-28
     */
    public static String getMonthEndDate(String input) {
        String[] time = input.split("-");
        Calendar calMonth = Calendar.getInstance();
        calMonth.set(Calendar.YEAR, Integer.parseInt(time[0]));
        calMonth.set(Calendar.MONTH, Integer.parseInt(time[1]) - 1);
        int endDay = calMonth.getActualMaximum(Calendar.DATE);
        return input + "-" + formatDate(endDay);
    }

    public static int getAge(String input) {
        String[] time = input.split("\\.");
        int birthYear = Integer.parseInt(time[0]);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        return year - birthYear;
    }

    public static String getTimeStr(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(timestamp));
    }

    public static String getDateStr(long timestamp) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(new Date(timestamp));
    }

    public static String addDay(String dateStr, int n) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Calendar cd = Calendar.getInstance();
            cd.setTime(sdf.parse(dateStr));
            cd.add(Calendar.DATE, n);//增加一天
            //cd.add(Calendar.MONTH, n);//增加一个月

            return sdf.format(cd.getTime());

        } catch (Exception e) {
            return null;
        }
    }

}
