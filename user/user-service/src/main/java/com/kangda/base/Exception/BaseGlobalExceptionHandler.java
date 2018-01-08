package com.kangda.base.Exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by: shouhan  on 15:51 2018/1/8.
 * <p>
 * 全局异常拦截实现(抽象类)
 */
public abstract class BaseGlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public String defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        String body = "系统异常";
        if (e instanceof UserNotLoginException) {
            body = "用户未登录";
        }
        addServerExceptionLog(req, e);
        return body;
    }

    protected abstract void addServerExceptionLog(HttpServletRequest req, Exception e);
}
