package com.manage.student.service.impl;

import com.manage.student.dao.TClass;
import com.manage.student.mapper.ClassMapper;
import com.manage.student.mapper.StudentClassMapper;
import com.manage.student.param.ClassParam;
import com.manage.student.utils.DateUtils;
import com.manage.student.utils.StringUtils;
import com.manage.student.service.ClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    private final static Logger logger = LoggerFactory.getLogger(ClassServiceImpl.class);

    @Autowired
    ClassMapper classMapper;

    @Autowired
    StudentClassMapper studentClassMapper;

    @Transactional
    @Override
    public int insertClass(ClassParam param) {
        int count = 0;
        // 校验班级名称是否重复
        if (!valClassName(param)) {
            logger.info("insertClass--班级名称重复");
            return count;
        }
        // 添加班级信息
        TClass tClass = new TClass();
        tClass.setClassId(StringUtils.GetUUID());
        tClass.setClassName(param.getClassName());
        tClass.setUpdateAt(DateUtils.GetNowStrDefault());
        count = classMapper.insertClass(tClass);
        return count;
    }

    @Transactional
    @Override
    public int delClassById(String classId) {
        int result = 0;
        if (studentClassMapper.countByClassId(classId) == 0) {
            logger.info("班级内未添加学生");
        } else {
            // 删除学生班级记录
            studentClassMapper.deleteByClassId(classId);
        }
        if (classMapper.countClassById(classId) == 0) {
            logger.info("delClassById--班级ID信息异常");
            result = 0;
        } else {
            // 删除班级信息
            result += classMapper.delClassById(classId);
        }
        return result;
    }

    @Transactional
    @Override
    public int updateClass(ClassParam param) {
        int count = 0;
        if (!valClassName(param)) {
            logger.info("updateClass--未通过班级名称重复校验");
            return count;
        }
        if (classMapper.countClassById(param.getClassId()) == 0) {
            logger.info("updateClass--班级ID参数异常");
            return count;
        }
        // 参数校验结束，进行更新班级信息
        TClass tClass = new TClass();
        tClass.setClassId(param.getClassId());
        tClass.setClassName(param.getClassName());
        tClass.setUpdateAt(DateUtils.GetNowStrDefault());
        count = classMapper.updateClassById(tClass);
        return count;
    }

    @Override
    public List<TClass> selectList(ClassParam param) {
        return classMapper.selectList(param);
    }

    @Override
    public int selectCount(ClassParam param) {
        return classMapper.selectCount(param);
    }

    @Override
    public List<TClass> selectAll() {
        return classMapper.selectAll();
    }

    @Override
    public Boolean valClassName(ClassParam param) {
        List<TClass> list = classMapper.valNameCount(param.getClassName());
        if (list.size() == 0) {
            // 班级名称不重复
            return true;
        }
        if (list.size() == 1 && list.get(0).getClassId().equals(param.getClassId())) {
            // 修改班级信息时 班级名称唯一，并且未发生改变
            return true;
        }
        return false;
    }
}
