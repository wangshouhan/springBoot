package com.kangda.base.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shouhan on 2017/8/24.
 * <p>
 * rabbitMq配置文件（绑定）
 */
@Configuration
public class rabbitConfig {
    //声明队列
    @Bean
    public Queue sendMessage() {
        return new Queue("sendMessage", true); // true表示持久化该队列
    }

    @Bean
    public Queue queue2() {
        return new Queue("findUser", true); // true表示持久化该队列
    }

    //声明交互器
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange("topicExchange");
    }

    //绑定
    @Bean
    public Binding binding1() {
        return BindingBuilder.bind(sendMessage()).to(topicExchange()).with("sendMessage");
    }

    //绑定
    @Bean
    public Binding binding2() {
        return BindingBuilder.bind(queue2()).to(topicExchange()).with("findUserMQ");
    }
}
