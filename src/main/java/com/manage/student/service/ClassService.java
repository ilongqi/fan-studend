package com.manage.student.service;

import com.manage.student.dao.TClass;
import com.manage.student.param.ClassParam;

import java.util.List;

public interface ClassService {

    /**
     * 添加班级信息
     * @param param
     * @return
     */
    int insertClass (ClassParam param);

    /**
     * 根据classId删除班级信息
     * @param classId
     * @return
     */
    int delClassById(String classId);

    /**
     * 根据修改班级信息
     * @param param
     * @return
     */
    int updateClass(ClassParam param);

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
     * 查询所有班级
     * @return
     */
    List<TClass> selectAll();

    /**
     * 校验班级名称，true校验通过，false校验失败
     * @param param
     * @return
     */
    Boolean valClassName(ClassParam param);

}
