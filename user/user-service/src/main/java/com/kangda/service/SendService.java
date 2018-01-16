package com.kangda.service;

import com.kangda.api.ISendService;
import com.kangda.base.socket.MessageAcceptor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.UUID;

/**
 * Created by shouhan on 2017/8/24.
 */
@Component
public class SendService implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback, ISendService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    public void init() {
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean b, String s) {
        if (b) {
            System.out.println("消息发送成功:" + correlationData);
        } else {
            System.out.println("消息发送失败:" + s);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println(message.getMessageProperties().getCorrelationIdString() + " 发送失败");
    }

    @Override
    public void findUserMQ(String msg) {
        System.out.println("开始发送消息 : " + msg.toLowerCase());
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange", "findUserMQ", msg, correlationId).toString();
        System.out.println("消费者响应 : " + response + " 消息处理完成");
    }

    public String sendMessage(MessageAcceptor acceptor){
        System.out.println("开始发送消息 : " + acceptor.toString());
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        String response = rabbitTemplate.convertSendAndReceive("topicExchange","sendMessage",acceptor,correlationId).toString();
        System.out.println("消费者响应 : " + response + " 消息处理完成");
        return acceptor.getMsg();
    }

}
