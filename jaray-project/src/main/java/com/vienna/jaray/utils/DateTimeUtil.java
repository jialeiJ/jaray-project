package com.vienna.jaray.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.MonthDay;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class DateTimeUtil {
	public static final String DATE_FORMAT_FULL = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_SHORT = "yyyy-MM-dd";
    public static final String DATE_FORMAT_COMPACT = "yyyyMMdd";
    public static final String DATE_FORMAT_COMPACTFULL = "yyyyMMddHHmmss";
    public static final String DATE_FORMAT_FULL_MSEL = "yyyyMMddHHmmssSSSS";
    public static final String DATE_YEAR_MONTH = "yyyyMM";
    public static final String DATE_FORMAT_FULL_MSE = "yyyyMMddHHmmssSSS";
    
    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static Date getCurrentDate() {
        return new Date();
    }
    
    /**
     * 获取系统当前日期
     *
     * @return
     */
    public static LocalDateTime getCurrentLocalDateTime() {
        return LocalDateTime.now();
    }
    
    /**
     * 根据时间格式返回对应的String类型的时间
     *
     * @param format
     * @return
     */
    public static String getCurDateTime(String format) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = now.format(dateTimeFormatter);
        return dataTime;
    }
    
    /**
     * 得到当前日期
     *
     * @return String 当前日期 yyyy-MM-dd HH:mm:ss格式
     */
    public static String getCurDateTimeFull() {
        return getCurDateTime(DATE_FORMAT_FULL);
    }
    
    /**
     * 得到当前日期
     *
     * @return String 当前日期 yyyyMMddHHmmss格式
     */
    public static String getCurDateTime1() {
        return getCurDateTime(DATE_FORMAT_FULL);
    }
    
    /**
     * 得到当前日期YYYYMM格式
     *
     * @return String 当前日期 yyyyMM格式
     */
    public static String getCurDateYYYYMM() {
        return getCurDateTime(DATE_YEAR_MONTH);
    }
    
    /**
     * 得到当前日期
     *
     * @return String 当前日期 yyyyMMdd格式
     */
    public static String getCurDateYYYYMMDD() {
        return getCurDateTime(DATE_FORMAT_COMPACT);
    }
    
    /**
     * 判断是否是今天
     *
     * @param strDate
     * @return
     */
    public static boolean isCurrentDay(String strDate) {
        boolean bRet = false;
        LocalDate strLocalDate = LocalDate.parse(strDate);
        if (LocalDate.now().getYear() == strLocalDate.getYear()) {
            MonthDay monthDay = MonthDay.from(strLocalDate);
            MonthDay today = MonthDay.from(LocalDate.now());
            return monthDay.equals(today);
        }
        return bRet;
    }
    
    /**
     * 获取几小时后的时间
     *
     * @param hour
     * @param format
     * @return
     */
    public static String getAfterDateTime(int hour, String format) {
        LocalTime localTime = LocalTime.now().plusHours(hour);
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        String dataTime = localTime.format(dateTimeFormatter);
        return dataTime;
    }
    
    /**
     * 当前日期时间戳(yyyyMMddHHmmssSSSS)
     *
     * @return
     */
    public static String getTimeStamp() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_FORMAT_FULL_MSEL);
        return now.format(dateTimeFormatter);
    }
    
    /**
     * 日期转字符串
     *
     * @return String
     */
    public static String parseDateToString(Date thedate, String format) {
        if (thedate != null) {
            Instant instant = thedate.toInstant();
            ZoneId zone = ZoneId.systemDefault();
            LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, zone);
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
            return localDateTime.format(dateTimeFormatter);
        }
        return null;
    }
    
    /**
     * parseDateToString(Date thedate, String format)的重载方法
     *
     * @param thedate
     * @return
     */
    public static String parseDateToString(Date thedate) {
        return parseDateToString(thedate, DATE_FORMAT_FULL);
    }
    
    /**
     * 字符串转日期
     *
     * @return Date
     */
    public static Date parseStringToDate(String thedate, String format) {
        DateFormat sdf = new SimpleDateFormat(format);
        Date date = null;
        try {
        	date = sdf.parse(thedate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 由String型日期转成format形式String
     *
     * @param srcFormat 原先格式
     * @param tarFormat 转化格式
     * @return String
     */
    public static String changeFormatDateString(String srcFormat, String tarFormat, String strDate) {
        if (strDate == null)
            return "";
        if (strDate.length() >= srcFormat.length() && srcFormat.length() >= tarFormat.length()) {
            return parseDateToString(parseStringToDate(strDate, srcFormat), tarFormat);
        }
        return strDate;
    }
    
    /**
     * 得到当前日期的前N天时间 yyyymmdd
     *
     * @param format
     * @param day
     * @return
     */
    public static String beforeNDaysDate(String format, int day) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        if (day > 0) {
            return LocalDateTime.now().minusDays(day).format(dateTimeFormatter);
        }
        return null;
    }

    /**
     * 得到时间的N分钟后的时间
     * @param theDate
     * @param nMinNum
     * @param format
     * @return
     */
    public static String beforeNMinsDate(String theDate, Integer nMinNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter).minusMinutes(nMinNum).format(dateTimeFormatter);
    }
    
    /**
     * 获得N个月后的日期
     * <p>
     * theDate 日期
     * <p>
     * int month 月数
     * <p>
     * format 格式
     */
    public static String afterNMonthDate(String theDate, int month, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter)
                .plusMonths(month)
                .format(dateTimeFormatter);
    }
    
    /**
     * 得到N天后的日期
     *
     * @param theDate 某日期
     *                格式 yyyy-MM-dd
     * @param nDayNum N天
     * @return String N天后的日期
     * @author kevin
     */
    public static String afterNDaysDate(String theDate, Integer nDayNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter)
                .plusDays(nDayNum)
                .format(dateTimeFormatter);
    }
    
    /**
     * 得到N小时后的日期
     *
     * @param theDate  时间
     * @param nHourNum N小时数
     * @param format   时间格式
     * @return
     */
    public static String afterNHoursDate(String theDate, Integer nHourNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter)
                .plusHours(nHourNum)
                .format(dateTimeFormatter);
    }
    
    /**
     * 得到N分钟后的日期
     *
     * @param theDate
     * @param nMinNum
     * @param format
     * @return
     */
    public static String afterNMinsDate(String theDate, Integer nMinNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter)
                .plusMinutes(nMinNum)
                .format(dateTimeFormatter);
    }
    
    /**
     * 得到N秒后的日期
     * 
     * @param theDate
     * @param nSecNum
     * @param format
     * @return
     */
    public static String afterNSecondsDate(String theDate, Integer nSecNum, String format) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        return LocalDateTime.parse(theDate,dateTimeFormatter)
                .plusSeconds(nSecNum)
                .format(dateTimeFormatter);
    }
    
    /**
     * 比较两个字符串格式日期大小,带格式的日期
     * 
     * @param srcDate
     * @param tarDate
     * @param format
     * @return
     */
    public static boolean isBefore(String srcDate, String tarDate, String format) {
        try {
            Date dat1 = parseStringToDate(srcDate, format);
            Date dat2 = parseStringToDate(tarDate, format);
            return dat1.before(dat2);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    /**
     * 比较两个字符串格式日期大小,带格式的日期,返回int
     * 
     * @param srcDate
     * @param tarDate
     * @param format
     * @return
     */
    public static long isBeforeInt(String srcDate, String tarDate, String format) {
        long result = 0;
        try {
            Date dat1 = parseStringToDate(srcDate, format);
            Date dat2 = parseStringToDate(tarDate, format);
            return dat2.getTime() - dat1.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    
    /**
     * 得到上一个月或者下一个月的日期
     * 
     * @param theDate
     * @param month
     * @param formatStr
     * @return
     */
    public static String getDayafterMonth(String theDate, int month, String formatStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formatStr);
        return LocalDateTime.parse(theDate).plusMonths(month).format(dateTimeFormatter);
    }
    
    /**
     * 将秒转换为小时分秒等
     *
     * @param sec
     * @return
     */
    public String changeTime(int sec) {
        String temp = "";
        if (sec < 60) {
            temp = "" + sec + "秒";
        } else if (sec < 3600) {
            temp = "" + sec / 60 + "分" + sec % 60 + "秒";
        } else {
            temp = "" + sec / 3600 + "小时" + (sec % 3600) / 60 + "分" + sec % 60 + "秒";
        }
        return temp;
    }
    
    /**
     * 计算两个日期相差天数
     *
     * @param end   结束日期
     * @param start 开始日期
     */
    public static int getSubDays(String end, String start) {
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        Long between = ChronoUnit.DAYS.between(startDate, endDate);
        return between.intValue();
    }
    
    public static String getTimeDiff(Date endTime, Date startTime) throws Exception {
        long l = endTime.getTime() - startTime.getTime();
        String returnStr = "";
        long day = l / (24 * 60 * 60 * 1000);
        if (day > 0) {
            returnStr += (day + "天");
        }
        long hour = (l / (60 * 60 * 1000) - day * 24);
        if (hour > 0) {
            returnStr += (hour + "小时");
        }
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        if (min > 0) {
            returnStr += (min + "分");
        }
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        if (s > 0) {
            returnStr += (s + "秒");
        }
        return returnStr;
    }

}
