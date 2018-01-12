package com.kangda.base.scheduler;

import com.kangda.api.ISendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by shouhan on 2017/8/24.
 * <p>
 * 定时器测试
 */
@Component
public class Scheduler {

    @Autowired
    private ISendService sendService;

    @Scheduled(fixedRate = 20000)//参考corn表达式
    public void testTasks() {
        System.out.println("每20秒执行一次。开始执行mq中的消息");
        sendService.findUserMQ("wang");
        System.out.println("每20秒执行一次。结束。");
    }
}
