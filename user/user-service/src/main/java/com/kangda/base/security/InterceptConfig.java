package com.kangda.base.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by: shouhan  on 15:32 2018/1/8.
 * <p>
 * 自定义拦截器配置
 */
@Configuration
public class InterceptConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //新建拦截器MyIntercept(暂时定义拦截所有的路径"/**")
        registry.addInterceptor(new MyIntercept())
                .excludePathPatterns("**/images/**", "**/js/**", "**/css/**");
    }

}
