package com.manage.student.mapper;

import com.manage.student.dao.TStudent;
import com.manage.student.param.StudentParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StudentMapper {

    /**
     * 添加学生信息
     * @param student
     * @return
     */
    int insertStudent (TStudent student);

    /**
     * 根据studentId删除学生信息
     * @param studentId
     * @return
     */
    int delStudentById(@Param("studentId") String studentId);

    /**
     * 根据studentId修改学生信息
     * @param student
     * @return
     */
    int updateStudentById(TStudent student);

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
    List<TStudent> selectListByClassId(@Param("classId") String classId);

    /**
     * 根据studentId 查询学生
     * @param studentId
     * @return
     */
    int countStudentById(@Param("studentId") String studentId);
}
