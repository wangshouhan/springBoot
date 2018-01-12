package com.kangda.base.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Created by: shouhan  on 16:22 2018/1/12.
 * <p>
 * 编写切面类
 */
@Component // 注册到Spring容器，必须加入这个注解
@Aspect // 该注解标示该类为切面类，切面是由通知和切点组成的。
public class ApiAspect {
    private static final Logger logger = LoggerFactory.getLogger(ApiAspect.class);
    ThreadLocal<Long> startTime = new ThreadLocal<Long>();

    @Pointcut("execution(public * com.kangda.controller..*.*(..))")  // 定义切点表达式
    public void controllerPoint() {
    }

    @Pointcut("@annotation(com.kangda.base.annotation.AspectIntercept)")// 定义注解类型的切点，只要方法上有该注解，都会匹配
    public void annotationPoint(){
    }

    /**
     * 定义前置通知
     */
    @Before("annotationPoint()")
    public void before(JoinPoint joinPoint) {
        System.out.println("方法执行前执行.....before");
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        logger.info("<====================================================================");
        logger.info("请求来源:  => " + request.getRemoteAddr());
        logger.info("请求URL: " + request.getRequestURL().toString());
        logger.info("请求方式: " + request.getMethod());
        logger.info("响应方法: " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("请求参数 : " + Arrays.toString(joinPoint.getArgs()));
        logger.info("---------------------------------------------------------------------");
        startTime.set(System.currentTimeMillis());
    }

    @Around("controllerPoint() && args(arg)")// 需要匹配切点表达式，同时需要匹配参数
    public String around(ProceedingJoinPoint pjp, String arg) {
        System.out.println("name:"+arg);
        System.out.println("方法环绕start....around.");
        String result = null;
        try {
            result = (String) pjp.proceed()+" aop String";
        } catch (Throwable e) {
            e.printStackTrace();
        }
        System.out.println("方法环绕end.....around");
        return result;
    }

    @After("annotationPoint()")
    public void after() {
        System.out.println("方法之后执行....after.");
    }

    @AfterReturning(pointcut="annotationPoint()", returning="rst")
    public void afterReturning(JoinPoint joinPoint, Object rst) {
        System.out.println("方法执行完执行.....afterReturning");
        logger.info("耗时（毫秒） : " + (System.currentTimeMillis() - startTime.get()));
        logger.info("返回数据: {}", rst);
        logger.info("====================================================================>");
    }

    @AfterThrowing("annotationPoint()")
    public void afterThrowing() {
        System.out.println("异常出现之后.....afterThrowing");
    }


}
