package com.manage.student.dao;

import java.io.Serializable;

public class TStudentClass implements Serializable {

    /**
     * 主键ID
     */
    private String id;

    /**
     * 学生ID
     */
    private String studentId;

    /**
     * 班级ID
     */
    private String classId;

    /**
     * 更新时间(yyyy-MM-dd HH:mm:ss)
     */
    private String updateAt;

    public TStudentClass() {
    }

    public TStudentClass(String id, String studentId, String classId, String updateAt) {
        this.id = id;
        this.studentId = studentId;
        this.classId = classId;
        this.updateAt = updateAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }
}
