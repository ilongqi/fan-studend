package com.manage.student.utils;

public class PageUtils {


    /**
     * (pageNo - 1) * pageSize
     * @param pageNo
     * @param pageSize
     * @return
     */
    public static Integer GetForm(Integer pageNo, Integer pageSize) {
        int form = (pageNo - 1) * pageSize;
        return (form < 0) ? 0 : form;
    }

}
