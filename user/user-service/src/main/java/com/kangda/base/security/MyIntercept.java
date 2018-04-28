package com.kangda.base.security;

import com.kangda.base.exception.UserNotLoginException;
import com.kangda.base.annotation.NoNeedLogin;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by: shouhan  on 15:36 2018/1/8.
 * <p>
 * 自定义拦截器拦截内容
 */
public class MyIntercept implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        return auth(httpServletRequest, httpServletResponse, (HandlerMethod) o);
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }

    /**
     * 拦截方法
     */
    private boolean auth(HttpServletRequest request, HttpServletResponse response, HandlerMethod method) throws UserNotLoginException {
        NoNeedLogin nnl = method.getMethodAnnotation(NoNeedLogin.class);
        if (nnl == null) {
            //如果没有不需要判断登录的注解判断其登录的token
            String token = request.getParameter("token");
            if (token == null || !token.equals("123456")){

                System.out.println("用户未登录。");
                //todo 寿汉 这里不支持静态文件的访问待优化
                //throw new UserNotLoginException();
            }
        }
        return true;
    }
}
