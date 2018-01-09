package com.kangda.base.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by: shouhan  on 15:48 2018/1/8.
 * <p>
 * 全局异常拦截
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseGlobalExceptionHandler {

    @Override
    protected void addServerExceptionLog(HttpServletRequest req, Exception e) {
        System.out.println("项目出错了^-^");
        System.out.println(e);
        System.out.println("错误日志记录中。。。。。。");
    }
}
