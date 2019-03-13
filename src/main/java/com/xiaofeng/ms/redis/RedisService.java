package com.xiaofeng.ms.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public <T> T get(BaseRedisKey baseKey, String key,Class<T> clazz){
        String realKey = baseKey.getRedisKey() + key;
        T object = stringToBean(redisTemplate.opsForValue().get(realKey),clazz);
        return object;
    }

    public <T> void set(BaseRedisKey baseKey, String key, T value){
        String realKey = baseKey.getRedisKey() + key;
        String str = beanToString(value);
        if(baseKey.getTimeOut() == 0 ){
            redisTemplate.opsForValue().set(realKey, str);
        }else {
            redisTemplate.opsForValue().set(realKey, str, baseKey.getTimeOut(), TimeUnit.SECONDS);
        }
    }

    public boolean exist(BaseRedisKey baseKey, String key){
        String realKey = baseKey.getRedisKey() + key;
        return redisTemplate.hasKey(realKey);
    }

    public boolean delete(BaseRedisKey baseKey, String key){
        String realKey = baseKey.getRedisKey() + key;
        return redisTemplate.delete(realKey);
    }

    public Long incr(BaseRedisKey baseKey, String key){
        String realKey = baseKey.getRedisKey() + key;
        return redisTemplate.opsForValue().increment(realKey);
    }

    public Long dec(BaseRedisKey baseKey, String key){
        String realKey = baseKey.getRedisKey() + key;
        return redisTemplate.opsForValue().decrement(realKey);
    }
    private <T> String beanToString(T value) {
        if(value == null) {
            return null;
        }
        Class<?> clazz = value.getClass();
        if(clazz == int.class || clazz == Integer.class) {
            return ""+value;
        }else if(clazz == String.class) {
            return (String)value;
        }else if(clazz == long.class || clazz == Long.class) {
            return ""+value;
        }else {
            return JSON.toJSONString(value);
        }
    }
    private <T> T stringToBean(Object obj, Class<T> clazz) {
        if(obj == null || clazz == null) {
            return null;
        }
        String str = obj.toString();
        if(clazz == int.class || clazz == Integer.class) {
            return (T)Integer.valueOf(str);
        }else if(clazz == String.class) {
            return (T)str;
        }else if(clazz == long.class || clazz == Long.class) {
            return  (T)Long.valueOf(str);
        }else {
            return JSON.toJavaObject(JSON.parseObject(str), clazz);
        }
    }


}
