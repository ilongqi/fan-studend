package com.manage.student.config;

import com.manage.student.common.Dictionary;
import com.manage.student.common.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;


/**
 * 设置全局异常捕捉
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Response<Object> customException(HttpServletRequest request, Exception e){
        logger.info("异常API--> " + request.getRequestURI());
        logger.info("Exception--> " + e.getMessage());
        e.printStackTrace();
        Response<Object> response = new Response<>(Dictionary.ErrorCode(), Dictionary.ErrorMessage());
        return response;
    }
}
