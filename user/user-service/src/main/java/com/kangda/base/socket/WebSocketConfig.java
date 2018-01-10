package com.kangda.base.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

/**
 * Created by: shouhan  on 14:36 2018/1/10.
 * <p>
 * WebSocket配置信息
 */
@Configuration
//通过@EnableWebSocketMessageBroker 注解凯旗使用STOMP协议来传输基于代理（message broker）的消息
@EnableWebSocketMessageBroker
//这时控制器支持使用@MessageMapping，就像使用@RequestMapping一样
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    //配置消息代码（Message Broker）
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //广播式应配置一个/topic消息代理
        config.enableSimpleBroker("/topic");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        //注册STOMP协议的节点，映射指定的URL，并指定使用SockJS协议
        registry.addEndpoint("/endpoint").withSockJS();
    }

}
