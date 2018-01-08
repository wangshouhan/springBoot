package com.kangda.service;

import com.kangda.api.IUserService;
import com.kangda.dao.UserDao;
import com.kangda.entity.User;
import com.kangda.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(String userName, String passWord) {
        User user = userDao.login(userName, passWord);
        return user;
    }

    @Override
    public User findByName(String userName) {
        return userDao.findByName(userName);
    }

    public List<User> findUserList(){
        return userMapper.findUserList();
    }
}
