package com.kangda.base.socket;

import java.io.Serializable;

/**
 * Created by: shouhan  on 16:34 2017/11/27.
 * <p>
 * 消息接收器
 */
public class MessageAcceptor implements Serializable {
    private String msg;

    public String getMsg() {
        return msg;
    }
}
