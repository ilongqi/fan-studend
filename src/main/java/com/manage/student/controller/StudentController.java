package com.manage.student.controller;

import com.manage.student.common.Dictionary;
import com.manage.student.common.RequestVerify;
import com.manage.student.common.Response;
import com.manage.student.dao.TStudent;
import com.manage.student.param.StudentParam;
import com.manage.student.utils.JacksonUtils;
import com.manage.student.utils.PageUtils;
import com.manage.student.service.impl.StudentServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Api(value = "学生相关API")
@Controller
@RequestMapping("/student")
public class StudentController {
    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @Autowired
    private StudentServiceImpl studentService;


    @ApiOperation(value="添加学生", notes="添加学生")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> addStudent(@RequestBody StudentParam param) throws Exception {
        logger.info("/student/add 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        int count = 0;
        // 校验学生姓名
        if (null == param || !RequestVerify.verClassName(param.getStudentName())) {
            logger.info("/student/add 学生信息异常");
            return new Response<>(Dictionary.FailCode(), "学生信息异常", count);
        }
        // 添加学生
        count = studentService.insertStudent(param);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "学生信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="删除学生", notes="删除学生")
    @RequestMapping(value = "/delete/{studentId}", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> delStudent(@PathVariable(value = "studentId")String studentId) throws Exception {
        logger.info("/student/delete 接口请求入参--<{}>", studentId);
        // 删除学生
        int count = studentService.delStudentById(studentId);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "学生ID信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="更新学生信息", notes="更新学生信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> updateStudent(@RequestBody StudentParam param) throws Exception {
        logger.info("/student/update 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        int count = 0;
        // 校验学生姓名
        if (null == param || !RequestVerify.verClassName(param.getStudentName())
                || !RequestVerify.verClassName(param.getStudentId())) {
            logger.info("/student/update 学生信息异常");
            return new Response<>(Dictionary.FailCode(), "学生信息异常", count);
        }
        // 修改学生
        count = studentService.updateStudent(param);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "学生信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="分页查询学生信息", notes="分页查询学生信息")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> getStudentList(@RequestBody StudentParam param) throws Exception {
        logger.info("/student/getList 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        Response<Object> response = new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage());
        // 处理分页参数
        Integer pageNo = (param.getPageNo() == null)?Dictionary.PageNo():param.getPageNo();
        Integer pageSize = param.getPageSize() == null?Dictionary.PageSize():param.getPageSize();
        pageSize = (pageSize < 0)?Dictionary.PageSize():pageSize;
        param.setPageSize(pageSize);
        param.setForm(PageUtils.GetForm(pageNo, pageSize));
        // 查询学生集合
        int total = studentService.selectCount(param);
        List<TStudent> tStudentList = new ArrayList<>();
        if (total > 0) {
            tStudentList = studentService.selectList(param);
        }
        // 封装出参
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", tStudentList);
        response.setData(result);
        logger.info("/student/getList 出参--<{}>", JacksonUtils.obj2json(response));
        return response;
    }

}
