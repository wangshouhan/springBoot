package com.kangda.base.security;

import org.springframework.security.core.AuthenticationException;


/**
 * Created by shouhan on 2017/8/23.
 * <p>
 * 登陆异常抛出
 */
public class CaptchaException extends AuthenticationException {

    public CaptchaException(String message) {
        super(message);
    }
}
