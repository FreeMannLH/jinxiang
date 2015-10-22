package ideatc.jinxiang.utils;

import java.io.UnsupportedEncodingException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class StringUtils {
    /**
     * 获取当前系统时间 yyyy-MM-dd HH:mm:ss
     * @return date
     */
    public static String getDate(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    /**
     * 获取当前系统时间 yyyy-MM-dd
     * @return date
     */
    public static String getDate1(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }

    /**
     * 获取当前系统时间  HH:mm:ss
     * @return date
     */
    public static String getDate2(){
        SimpleDateFormat sDateFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        String date = sDateFormat.format(new java.util.Date());
        return date;
    }












    public static String DecodeString(String source, String code) throws UnsupportedEncodingException {
        String tm = new String(source.getBytes("iso8859-1"), code);
        return tm;
    }

    public static boolean isStringEmt(String source) {
        return source == null || "".equals(source) || "null".equals(source);

    }

    public static boolean isLenghtGt(String source, int length) {
        return source.length() > length;

    }


    public static Date StringToDate(String str, String patter, ParsePosition pos) {
        SimpleDateFormat formatter = new SimpleDateFormat(patter);
        // String dateString = formatter.format(currentTime);
        if (pos == null) pos = new ParsePosition(0);
        Date currentTime_2 = formatter.parse(str, pos);
        return currentTime_2;
    }

    /**
     * 日期转字符串
     * @param time
     * @param patter
     * @return
     */
    public static String DateToString(Date time, String patter) {
        SimpleDateFormat formatter = new SimpleDateFormat(patter);
        return formatter.format(time);
    }

    public static String DataEncreate(int days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        return DateToString(c.getTime(), "MM/dd");
    }

    public static String DataEncreate(Date startdt, int days) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startdt);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return DateToString(calendar.getTime(), "MM/dd");
    }

    public static String getWeekByDate(Date dt) {
        String[] weekDays = {"����", "����", "��һ", "�ܶ�", "����", "����", "����",};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        cal.setTimeZone(TimeZone.getDefault());
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) w = 0;
        return weekDays[w];
    }

    public static String subString(String source, int length) {
        if (source.length() <= length) {
            return source;
        } else return source.substring(0, length);
    }

    /**
     * 时间戳转日期
     * @param dateStr
     * @return
     */
//    public static String getDataByString(String dateStr) {
//        String date = dateStr.substring(6,18);
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String sd = sdf.format(new Date(Long.parseLong(date)));
//        return sd;
//    }
}
