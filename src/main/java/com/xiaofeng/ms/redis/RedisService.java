package com.xiaofeng.ms.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate redisTemplate;

    public Object get(BaseRedisKey baseKey, String key){
        String realKey = baseKey.getRedisKey() + key;
        return redisTemplate.opsForValue().get(realKey);
    }

    public void set(BaseRedisKey baseKey, String key, Object value){
        String realKey = baseKey.getRedisKey() + key;
        if(baseKey.getTimeOut() == 0 ){
            redisTemplate.opsForValue().set(realKey, value);
        }else {
            redisTemplate.opsForValue().set(realKey, value, baseKey.getTimeOut(), TimeUnit.SECONDS);
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


}
