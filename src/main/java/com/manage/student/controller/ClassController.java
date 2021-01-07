package com.manage.student.controller;

import com.manage.student.common.Dictionary;
import com.manage.student.common.RequestVerify;
import com.manage.student.common.Response;
import com.manage.student.dao.TClass;
import com.manage.student.dao.TStudent;
import com.manage.student.param.ClassParam;
import com.manage.student.utils.JacksonUtils;
import com.manage.student.utils.PageUtils;
import com.manage.student.service.impl.StudentServiceImpl;
import com.manage.student.service.impl.ClassServiceImpl;
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

@Api(value = "班级相关API")
@Controller
@RequestMapping("/class")
public class ClassController {
    private final static Logger logger = LoggerFactory.getLogger(ClassController.class);

    @Autowired
    private ClassServiceImpl classService;
    @Autowired
    private StudentServiceImpl studentService;

    @ApiOperation(value="添加班级", notes="添加班级")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> addClass(@RequestBody ClassParam param) throws Exception {
        logger.info("/class/add 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        int count = 0;
        // 校验班级名称
        if (null == param || !RequestVerify.verClassName(param.getClassName())) {
            logger.info("/class/add 班级信息异常");
            return new Response<>(Dictionary.FailCode(), "班级信息异常", count);
        }
        // 添加班级
        count = classService.insertClass(param);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "班级信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="删除班级", notes="删除班级")
    @RequestMapping(value = "/delete/{classId}", method = RequestMethod.GET)
    @ResponseBody
    public Response<Object> delClass(@PathVariable(value = "classId")String classId) throws Exception {
        logger.info("/class/delete 接口请求入参--<{}>", classId);
        // 删除班级
        int count = classService.delClassById(classId);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "班级ID信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="更新班级信息", notes="更新班级信息")
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> updateClass(@RequestBody ClassParam param) throws Exception {
        logger.info("/class/update 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        int count = 0;
        if (null == param || !RequestVerify.verClassName(param.getClassName())
                || !RequestVerify.verParam(param.getClassId())) {
            logger.info("/class/update 班级信息异常");
            return new Response<>(Dictionary.FailCode(), "班级信息异常", count);
        }
        // 修改班级
        count = classService.updateClass(param);
        if (count == 0) {
            return new Response<>(Dictionary.FailCode(), "班级信息异常", count);
        }
        return new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage(), count);
    }

    @ApiOperation(value="分页查询班级信息", notes="分页查询班级信息")
    @RequestMapping(value = "/getList", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> getClassList(@RequestBody ClassParam param) throws Exception {
        logger.info("/class/getList 接口请求入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        // 处理分页参数
        Integer pageNo = param.getPageNo() == null?Dictionary.PageNo():param.getPageNo();
        Integer pageSize = param.getPageSize() == null?Dictionary.PageSize():param.getPageSize();
        pageSize = (pageSize < 0)?Dictionary.PageSize():pageSize;
        param.setPageSize(pageSize);
        param.setForm(PageUtils.GetForm(pageNo, pageSize));
        // 查询班级集合
        int total = classService.selectCount(param);
        List<TClass> tClassList = new ArrayList<>();
        if (total > 0) {
            tClassList = classService.selectList(param);
            for (int i = 0; i < tClassList.size(); i++) {
                List<TStudent> studentList = studentService.selectStudentList(tClassList.get(i).getClassId());
                tClassList.get(i).setStudentList(studentList);
            }
        }
        // 封装出参
        Map<String, Object> result = new HashMap<>();
        result.put("total", total);
        result.put("list", tClassList);
        Response<Object> response = new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage());
        response.setData(result);
        logger.info("/class/getList 出参--<{}>", JacksonUtils.obj2json(response));
        return response;
    }

    @ApiOperation(value="查询所有班级", notes="查询所有班级")
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> getAllClass() throws Exception {
        logger.info("/class/getAll 接口");
        // 查询班级集合
        List<TClass> tClassList = classService.selectAll();
        // 封装出参
        Response<Object> response = new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage());
        response.setData(tClassList);
        logger.info("/class/getAll 出参--<{}>", JacksonUtils.obj2json(response));
        return response;
    }

    @ApiOperation(value="校验班级名称", notes="校验班级名称")
    @RequestMapping(value = "/valClassName", method = RequestMethod.POST)
    @ResponseBody
    public Response<Object> valClassName(@RequestBody ClassParam param) throws Exception {
        logger.info("/class/valClassName 接口入参--<{}>", JacksonUtils.obj2jsonIgnoreNull(param));
        // 班级名称校验结果
        Boolean flag = false;
        if (null == param || !RequestVerify.verClassName(param.getClassName())) {
            logger.info("/class/valClassName 班级名称信息异常");
        } else {
            flag = classService.valClassName(param);
        }
        // 封装出参
        Response<Object> response = new Response<>(Dictionary.SuccessCode(), Dictionary.SuccessMessage());
        response.setData(flag);
        logger.info("/class/valClassName 出参--<{}>", JacksonUtils.obj2json(response));
        return response;
    }

}
