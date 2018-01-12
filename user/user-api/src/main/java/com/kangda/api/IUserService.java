package com.kangda.api;

import com.kangda.entity.User;

import java.util.List;

/**
 * Created by shouhan on 2017/8/22.
 * <p>
 * 用户api
 */
public interface IUserService {
    User findByName(String userName);

    List<User> findUserList();

    void updateColor(String color);

    User findById(Integer id);
}
