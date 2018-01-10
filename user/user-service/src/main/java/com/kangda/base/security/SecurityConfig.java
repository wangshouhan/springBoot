package com.kangda.base.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Created by shouhan on 2017/8/23.
 */
@Service
@Configuration
//禁用Boot的默认Security配置，配合@Configuration启用自定义配置（需要扩展WebSecurityConfigurerAdapter）
@EnableWebSecurity
//启用Security注解，例如最常用的@PreAuthorize
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailsService detailsService;

    /**
     * configure(HttpSecurity): Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                //允许所有用户访问"/"和"/home"
                .antMatchers("/", "/").permitAll()
                //其他地址的访问均需验证权限
                .anyRequest().authenticated()
                .and()
                .formLogin()
                //指定登录页是"/login"
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/home")//登录成功后默认跳转到"/home"
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/user/logout")//退出登录的链接
                .logoutSuccessUrl("/user/login")//退出登录后的默认url是"/login"
                .permitAll();
    }

    /**
     * configure(WebSecurity): Web层面的配置，一般用来配置无需安全检查的路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/image/**", "/**/favicon.ico");
    }

    /**
     * configure(AuthenticationManagerBuilder): 身份验证配置，用于注入自定义身份验证Bean和密码校验
     */
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        //使用BCrypt加密算法
        auth.userDetailsService(detailsService).passwordEncoder(new BCryptPasswordEncoder());
        //auth.userDetailsService(detailsService).passwordEncoder(passwordEncoder());
        //authenticationProvider.setPasswordEncoder(passwordEncoder());
        //auth.authenticationProvider(authenticationProvider)
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }
}
