package com.kangda.service;

import com.kangda.api.IUserService;
import com.kangda.base.socket.MessageAcceptor;
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
     * @param messageAdapter
     * @return
     */
    @RabbitListener(queues = "sendMessage")
    public String sendMessage(MessageAcceptor messageAdapter) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自sendMessage队列的消息：" + messageAdapter.toString());
        return messageAdapter.getMsg();
    }

    /**
     * 定时器mq任务
     *
     * @param msg
     * @return
     */
    @RabbitListener(queues = "findUser")
    public String findUser(String msg) {
        System.out.println(Thread.currentThread().getName() + " 接收到来自findShopMQ队列的消息：" + msg);
        User user = userService.findByName(msg);
        System.out.println(user.getId());
        System.out.println(user.getUserName());
        System.out.println(user.getPassWord());
        return msg.toUpperCase();
    }

}
