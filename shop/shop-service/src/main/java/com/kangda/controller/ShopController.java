package com.kangda.controller;

import com.kangda.api.ISendService;
import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.base.redis.RedisConfig;
import com.kangda.entity.Shop;
import com.kangda.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shouhan on 2017/8/22.
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private IShopService shopService;
    @Autowired
    private IUserService userService;
    @Autowired
    private RedisConfig redisConfig;
    @Autowired
    private ISendService sendService;

    /**
     * 对外暴露的接口shop详情
     *
     * @param id
     * @return
     */
    @RequestMapping("detail")
    public Shop detail(Integer id) {
        return shopService.findById(id);
    }

    /**
     * redis获取User模块登录时保存的用户信息
     *
     * @return
     */
    @RequestMapping("redis")
    public User redis() {
        return (User) redisConfig.getMap("user");
    }

    /**
     * 远程调用user模块的接口
     *
     * @return
     */
    @RequestMapping("test")
    public User test() {
        return userService.login("admin", "123456");
    }

    /**
     * rabbitMQ消息队列远程调用测试
     */
    @RequestMapping("send")
    public void send() {
        sendService.send("Hello RabbitMQ...");
    }

}
