package com.kangda.api;


/**
 * Created by shouhan on 2017/8/24.
 */
public interface ISendService {
    /**
     * 发送消息，不需要实现任何接口，供外部调用。
     *
     * @param msg
     */
    void send(String msg);

    void findUserMQ(String msg);
}
