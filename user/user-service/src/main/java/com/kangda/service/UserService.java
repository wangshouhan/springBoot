package com.kangda.service;

import com.github.pagehelper.PageHelper;
import com.kangda.api.IUserService;
import com.kangda.entity.Page;
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
    private UserMapper userMapper;

    @Override
    public User findByName(String userName) {
        return userMapper.findByName(userName);
    }

    @Override
    public List<User> findUserList() {
        return userMapper.findUserList();
    }

    @Override
    public void updateColor(String color) {
        userMapper.updateColor(color);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }

    @Override
    public Page<User> findPage(Page<User> page) {
        com.github.pagehelper.Page pageHelper = PageHelper.startPage(page.getPageNo(),page.getPageSize());
        pageDate(page,userMapper.findUserList(),pageHelper);
        return page;
    }

    private void pageDate(Page<User> page, List<User> list, com.github.pagehelper.Page pageHelper) {
        page.setTotalCount(pageHelper.getTotal());
        page.setTotalNo(pageHelper.getPageNum());
        page.setResult(list);
    }
}
