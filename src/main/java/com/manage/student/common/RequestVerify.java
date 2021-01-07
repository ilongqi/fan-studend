package com.manage.student.common;

import com.manage.student.utils.StringUtils;

/**
 * 请求入参校验
 */
public class RequestVerify {

    /**
     * 检验参数 是否为空 true 非空 校验通过  false 空 校验失败
     * @param param
     * @return
     */
    public static Boolean verParam (String param) {
        return StringUtils.isNotEmpty(param);
    }

    /**
     * 检验班级名称 空判断和字符长度判断
     * 不为空且字符长度 小于等于 100 则为校验通过
     * @param className
     * @return
     */
    public static Boolean verClassName (String className) {
        return verLength(className, 0, 100);
    }

    /**
     * 检验学生姓名 空判断和字符长度判断
     * 不为空且字符长度 小于等于 100 则为校验通过
     * @param studentName
     * @return
     */
    public static Boolean verStudentName (String studentName) {
        return verLength(studentName, 0, 100);
    }

    /**
     * 校验字符串长度 max >= min  字符串长度 [min, max]
     * @param value
     * @param min
     * @param max
     * @return
     */
    private static Boolean verLength (String value, int min, int max) {
        if (StringUtils.isEmpty(value)) {
            return false;
        }
        if (max >= min) {
            int valueLen = value.length();
            return (valueLen - min) * (max - valueLen) >= 0;
        } else {
            return false;
        }
    }
}
