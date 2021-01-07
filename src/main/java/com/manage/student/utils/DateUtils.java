package com.manage.student.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtils {

    private final static String TimeFormat = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间 默认格式 yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String GetNowStrDefault() {
        return GetNowStr(TimeFormat);
    }

    /**
     * 获取当前时间
     * @return
     */
    public static String GetNowStr(String format) {
        String timeStr = LocalDateTime.now().format(DateTimeFormatter.ofPattern(format));
        return timeStr;
    }
}
