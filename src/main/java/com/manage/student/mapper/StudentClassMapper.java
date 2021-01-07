package com.manage.student.mapper;


import com.manage.student.dao.TStudentClass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StudentClassMapper {

    /**
     * 添加学生班级信息
     * @param studentClass
     * @return
     */
    int insertStudentClass (TStudentClass studentClass);

    /**
     * 根据studentId删除学生班级信息
     * @param studentId
     * @return
     */
    int deleteByStudentId (@Param("studentId") String studentId);

    /**
     * 根据classId删除学生班级信息
     * @param classId
     * @return
     */
    int deleteByClassId (@Param("classId") String classId);


    /**
     * 根据studentId查询 学生班级记录
     * @param studentId
     * @return
     */
    int countByStudentId(@Param("studentId") String studentId);

    /**
     * 根据classId查询 学生班级记录
     * @param classId
     * @return
     */
    int countByClassId (@Param("classId") String classId);
}
