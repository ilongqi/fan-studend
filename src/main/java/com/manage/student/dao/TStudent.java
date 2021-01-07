package com.manage.student.dao;

import java.io.Serializable;

public class TStudent implements Serializable {

    /**
     * 主键
     */
    private String studentId;

    /**
     * 学生姓名
     */
    private String studentName;

    /**
     * 更新时间(yyyy-MM-dd HH:mm:ss)
     */
    private String updateAt;

    /**
     * 学生的班级ID
     */
    private String classId;

    /**
     * 学生的班级名称
     */
    private String className;

    public TStudent() {
    }

    public TStudent(String studentId, String studentName, String updateAt) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.updateAt = updateAt;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
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
}
