package com.manage.student.common;

/**
 * 系统字典值
 */
public class Dictionary {

    // 请求返回的 Code 及 Message
    public static String SuccessCode() {
        return "000";
    }
    public static String SuccessMessage() {
        return "请求成功";
    }

    // 请求失败
    public static String ErrorCode() {
        return "999";
    }
    public static String ErrorMessage() {
        return "请求失败";
    }

    // 新增修改删除时，操作失败，FailMessage
    public static String FailCode() {
        return "998";
    }
    public static String FailMessage() {
        return "操作失败";
    }

    public static String NoRightCode() {
        return "001";
    }
    public static String NoRightMessage() {
        return "无权访问";
    }

    public static String OverTimeCode() {
        return "002";
    }
    public static String OverTimeMessage() {
        return "请求超时";
    }

    // 默认 PageNo 为 1  PageSize 为 10
    public static Integer PageNo () {
        return 1;
    }

    public static Integer PageSize () {
        return 10;
    }

}
