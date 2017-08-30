package com.kangda.dao;

import com.kangda.entity.Shop;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 */
@Service
public class ShopDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    public Shop findById(Integer id) {
        Map map = jdbcTemplate.queryForMap("select * from shop where id = "+id);
        if(map!=null && map.size()>0){
            Shop shop = new Shop();
            shop.setId(Long.valueOf(map.get("id").toString()));
            shop.setName(map.get("name").toString());
            shop.setPrice((Double) map.get("price"));
            shop.setCreateTime((Date) map.get("create_time"));
            return shop;
        }
        return null;
    }
}
