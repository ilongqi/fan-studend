package com.manage.student.dao;

import java.io.Serializable;
import java.util.List;

public class TClass implements Serializable {

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 班级名称(不可重复)
     */
    private String className;

    /**
     * 更新时间(yyyy-MM-dd HH:mm:ss)
     */
    private String updateAt;

    /**
     * 班级内学生
     */
    private List<TStudent> studentList;

    public TClass() {
    }

    public TClass(String classId, String className, String updateAt) {
        this.classId = classId;
        this.className = className;
        this.updateAt = updateAt;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    public List<TStudent> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<TStudent> studentList) {
        this.studentList = studentList;
    }
}
