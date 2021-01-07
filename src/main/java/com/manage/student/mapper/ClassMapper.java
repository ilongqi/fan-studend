package com.manage.student.mapper;

import com.manage.student.dao.TClass;
import com.manage.student.param.ClassParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClassMapper {

    /**
     * 添加班级信息
     * @param tClass
     * @return
     */
    int insertClass (TClass tClass);

    /**
     * 根据classId删除班级信息
     * @param classId
     * @return
     */
    int delClassById(@Param("classId") String classId);

    /**
     * 根据classId更新班级信息
     * @param tClass
     * @return
     */
    int updateClassById(TClass tClass);

    /**
     * 根据参数，分页查询班级信息
     * @param param
     * @return
     */
    List<TClass> selectList(ClassParam param);

    /**
     * 根据参数，查询班级数量
     * @param param
     * @return
     */
    int selectCount(ClassParam param);

    /**
     * 查询所有班级 只查询 class_id 和 class_name
     * @return
     */
    List<TClass> selectAll();

    /**
     * 根据班级名称查询班级集合
     * @param className
     * @return
     */
    List<TClass> valNameCount(@Param("className") String className);

    /**
     * 根据classId 查询班级
     * @param classId
     * @return
     */
    int countClassById(@Param("classId") String classId);
}
