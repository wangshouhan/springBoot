package com.kangda.service;

import com.kangda.api.IUserService;
import com.kangda.dao.UserDao;
import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
    @Override
    public User login(String userName, String passWord) {
        User user = userDao.login(userName, passWord);
        return user;
    }

    @Override
    public User findByName(String userName) {
        return userDao.findByName(userName);
    }
}
