package com.kangda.dao;

import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class UserDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public User login(String userName, String passWord) {
        try {
            Map map = jdbcTemplate.queryForMap("select * from user where user_name = 'wang'");
            return formatUser(map);
        }catch (Exception e){
            return null;
        }
    }

    public User findByName(String userName) {
        try {
            Map map = jdbcTemplate.queryForMap("select * from user where user_name = '"+userName+"'");
            return formatUser(map);
        }catch (Exception e){
            return null;
        }
    }

    public User formatUser(Map map){
        User user = new User();
        user.setId((long) Integer.parseInt(map.get("id").toString()));
        user.setUserName(map.get("user_name").toString());
        user.setPassWord(map.get("pass_word").toString());
        return user;
    }
}
