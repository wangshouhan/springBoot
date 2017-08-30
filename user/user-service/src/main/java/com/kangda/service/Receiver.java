package com.kangda.service;

import com.kangda.api.IShopService;
import com.kangda.api.IUserService;
import com.kangda.entity.Shop;
import com.kangda.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by shouhan on 2017/8/24.
 */
@Component
public class Receiver {
    @Autowired
    private IUserService userService;

    /**
     * 接收mq传过来的消息
     *
     * @param msg
     * @return
     */
    @RabbitListener(queues = "say")
    public String processMessage1(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自say队列的消息：" + msg);
        return msg.toUpperCase();
    }

    /**
     * 定时器mq任务
     *
     * @param msg
     * @return
     */
    @RabbitListener(queues = "findUser")
    public String processMessage2(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自findShopMQ队列的消息：" + msg);
        User user = userService.findByName(msg);
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        return msg.toUpperCase();
    }

}
