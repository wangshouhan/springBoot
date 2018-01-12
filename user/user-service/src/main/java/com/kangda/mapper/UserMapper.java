package com.kangda.mapper;

import com.kangda.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by: shouhan  on 16:57 2018/1/8.
 */
@Mapper
public interface UserMapper {
    List<User> findUserList();

    void updateColor(String color);

    User findByName(String userName);

    User findById(Integer id);
}
