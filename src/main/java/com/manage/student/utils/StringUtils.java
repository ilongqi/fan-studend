package com.manage.student.utils;

import java.util.UUID;

/**
 * String 工具
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

    public static String GetUUID () {
        String uuid = UUID.randomUUID().toString();
        return uuid.replace("-", "");
    }
}
