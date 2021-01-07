package com.manage.student.service.impl;

import com.manage.student.dao.TStudent;
import com.manage.student.dao.TStudentClass;
import com.manage.student.mapper.ClassMapper;
import com.manage.student.mapper.StudentClassMapper;
import com.manage.student.mapper.StudentMapper;
import com.manage.student.param.StudentParam;
import com.manage.student.service.StudentService;
import com.manage.student.utils.DateUtils;
import com.manage.student.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final static Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private StudentClassMapper studentClassMapper;

    @Autowired
    private ClassMapper classMapper;

    @Transactional
    @Override
    public int insertStudent(StudentParam param) {
        int result = 0;
        if (StringUtils.isNotEmpty(param.getClassId())) {
            // 验证班级ID是否存在
            int countClass = classMapper.countClassById(param.getClassId());
            if (countClass == 0) {
                // 班级信息不存在
                return result;
            }
        }
        // 添加学生信息
        TStudent student = new TStudent();
        student.setStudentId(StringUtils.GetUUID());
        student.setStudentName(param.getStudentName());
        student.setUpdateAt(DateUtils.GetNowStrDefault());
        result += studentMapper.insertStudent(student);
        if (StringUtils.isNotEmpty(param.getClassId())) {
            // 添加学生班级信息
            TStudentClass studentClass = new TStudentClass();
            studentClass.setId(StringUtils.GetUUID());
            studentClass.setStudentId(student.getStudentId());
            studentClass.setClassId(param.getClassId());
            studentClass.setUpdateAt(DateUtils.GetNowStrDefault());
            result += studentClassMapper.insertStudentClass(studentClass);
        }
        return result;
    }

    @Transactional
    @Override
    public int delStudentById(String studentId) {
        int count = 0;
        if (studentMapper.countStudentById(studentId) == 0) {
            logger.info("delStudentById--学生ID信息异常");
            return count;
        }
        if (studentClassMapper.countByStudentId(studentId) > 0) {
            // 删除该学生的班级记录
            studentClassMapper.deleteByStudentId(studentId);
        }
        // 删除学生信息
        count += studentMapper.delStudentById(studentId);
        return count;
    }

    @Transactional
    @Override
    public int updateStudent(StudentParam param) {
        int count = 0;
        if (StringUtils.isNotEmpty(param.getClassId())) {
            // 验证班级ID是否存在
            int countClass = classMapper.countClassById(param.getClassId());
            if (countClass == 0) {
                // 班级信息不存在
                return count;
            }
        }
        if (studentMapper.countStudentById(param.getStudentId()) == 0) {
            logger.info("updateClass--学生ID参数异常");
            return count;
        }
        // 参数校验结束，进行学生信息更新
        if (studentClassMapper.countByStudentId(param.getStudentId()) > 0) {
            // 删除学生班级记录
            count += studentClassMapper.deleteByStudentId(param.getStudentId());
        }
        // 修改学生信息
        TStudent student = new TStudent();
        student.setStudentId(param.getStudentId());
        student.setStudentName(param.getStudentName());
        student.setUpdateAt(DateUtils.GetNowStrDefault());
        studentMapper.updateStudentById(student);
        // 添加学生班级信息
        TStudentClass studentClass = new TStudentClass();
        studentClass.setId(StringUtils.GetUUID());
        studentClass.setStudentId(student.getStudentId());
        studentClass.setClassId(param.getClassId());
        studentClass.setUpdateAt(DateUtils.GetNowStrDefault());
        count += studentClassMapper.insertStudentClass(studentClass);
        return count;
    }

    @Override
    public List<TStudent> selectList(StudentParam param) {
        return studentMapper.selectList(param);
    }

    @Override
    public int selectCount(StudentParam param) {
        return studentMapper.selectCount(param);
    }

    @Override
    public List<TStudent> selectStudentList(String classId) {
        return studentMapper.selectListByClassId(classId);
    }
}
