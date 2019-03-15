package com.xiaofeng.ms.redis;

public class UserRedisKey extends BaseRedisKey{
    public static final int TOKEN_EXPIRE = 3600*24 * 2;
    private UserRedisKey(int expireSeconds, String prefix) {
        super(expireSeconds, prefix);
    }
    public static UserRedisKey token = new UserRedisKey(TOKEN_EXPIRE, "tk");
    public static UserRedisKey getById = new UserRedisKey(0, "id");
}
