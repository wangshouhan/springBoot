package com.kangda.dao;

import com.kangda.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class ShopDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public List findById(Integer userId) {
        List<Map<String,Object>> list = jdbcTemplate.queryForList("select * from shop where userId = " + userId);
        return list;
    }
}
