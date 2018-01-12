package com.kangda.base.annotation;

import java.lang.annotation.*;

/**
 * Created by: shouhan  on 16:29 2018/1/12.
 * <p>
 * 定义进入切面的注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface AspectIntercept {
}
