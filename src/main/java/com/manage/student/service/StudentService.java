package com.manage.student.service;

import com.manage.student.dao.TStudent;
import com.manage.student.param.StudentParam;

import java.util.List;

public interface StudentService {

    /**
     * 添加学生信息
     * @param param
     * @return
     */
    int insertStudent (StudentParam param);

    /**
     * 根据studentId删除学生信息
     * @param studentId
     * @return
     */
    int delStudentById(String studentId);

    /**
     * 修改学生信息
     * @param param
     * @return
     */
    int updateStudent(StudentParam param);

    /**
     * 根据参数，分页查询学生信息
     * @param param
     * @return
     */
    List<TStudent> selectList(StudentParam param);

    /**
     * 根据参数，查询学生数量
     * @param param
     * @return
     */
    int selectCount(StudentParam param);

    /**
     * 查询班级内学生信息
     * @param classId
     * @return
     */
    List<TStudent> selectStudentList(String classId);
}
