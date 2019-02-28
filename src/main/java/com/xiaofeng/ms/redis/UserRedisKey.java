package com.xiaofeng.ms.redis;

public class UserRedisKey extends BaseRedisKey{
    private UserRedisKey(String key){
        super(key);
    }
    public static UserRedisKey userRedisKeyById = new UserRedisKey("id");
    public static UserRedisKey userRedisKeyByName = new UserRedisKey("name");
}
