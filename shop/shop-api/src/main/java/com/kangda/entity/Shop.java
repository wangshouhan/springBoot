package com.kangda.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by shouhan on 2017/8/22.
 */
public class Shop implements Serializable{
    /**
     * 主键id
     */
    private Long id;
    /**
     * 商品名称
     */
    private String name;
    /**
     * 商品价格
     */
    private double price;
    /**
     * 创建时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
