package com.kangda.base.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

/**
 * Created by shouhan on 2017/8/23.
 */
@Repository
public class RedisConfig {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置String缓存
     *
     * @param key
     * @param value
     */
    public void setKey(String key, String value) {
        ValueOperations<String, String> ops = stringRedisTemplate.opsForValue();
        ops.set(key, value, 1, TimeUnit.MINUTES);//1分钟过期
    }

    /**
     * 获取String缓存
     *
     * @param key
     * @return
     */
    public String getValue(String key) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        return ops.get(key);
    }

    /**
     * 设置Object缓存
     *
     * @param key
     * @param obj
     */
    public void setMap(String key, Object obj) {
        redisTemplate.opsForValue().set(key, obj);
    }

    /**
     * 获取Object缓存
     *
     * @param key
     * @return
     */
    public Object getMap(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除redis缓存
     */
    public void remove(String key) {
        redisTemplate.delete(key);
    }
}
