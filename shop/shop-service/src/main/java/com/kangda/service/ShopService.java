package com.kangda.service;

import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.base.redis.RedisConfig;
import com.kangda.dao.ShopDao;
import com.kangda.entity.Shop;
import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by shouhan on 2017/8/22.
 * <p>
 * 商品详情业务处理层
 */
@Service
public class ShopService implements IShopService {

    @Autowired
    private ShopDao shopDao;
    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private IUserService userService;

    @Override
    public Map<String, Object> findByUserId() {
        Map<String, Object> map = new HashMap<>();
        Integer userId = Integer.parseInt(redisConfig.getMap("userId").toString());
        User user = userService.findById(userId);
        List shopList = shopDao.findById(userId);
        map.put("user", user);
        map.put("shopList", shopList);
        return map;
    }
}
