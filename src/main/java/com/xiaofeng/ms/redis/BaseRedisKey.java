package com.xiaofeng.ms.redis;

/**
 * redis key生成
 */
public abstract class BaseRedisKey {
    private int timeOut;//秒
    private String redisKey;
    public BaseRedisKey(String redisKey){
        this.redisKey = redisKey;
        this.timeOut = 0;
    }
    public BaseRedisKey(int timeOut, String redisKey){
        this.timeOut = timeOut;
        this.redisKey = redisKey;
    }

    public int getTimeOut(){
        return this.timeOut;
    }
    public String getRedisKey(){
        String key = getClass().getName() + ":" +redisKey;
        return key;
    }
}
