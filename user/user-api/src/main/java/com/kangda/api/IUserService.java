package com.kangda.api;

import com.kangda.entity.User;

/**
 * Created by shouhan on 2017/8/22.
 */
public interface IUserService {
    User login(String userName, String passWord);

    User findByName(String userName);
}
