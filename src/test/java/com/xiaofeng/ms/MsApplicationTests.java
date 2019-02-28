package com.xiaofeng.ms;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisConfig;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.redis.UserRedisKey;
import com.xiaofeng.ms.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;
    @Test
    public void contextLoads() {
    }
    @Test
    public void testDb(){
        User user = userService.getUserById(1);
        System.out.println(user.getName());
    }

    @Test
    public void testTran(){
        userService.getTran();
    }
    @Test
    public void testRedis(){
        String key = UserRedisKey.userRedisKeyById.getRedisKey() + "1";
        redisTemplate.opsForValue().set(key,"2019年2月28日16:37:51");
        System.out.println(redisTemplate.opsForValue().get(key));
    }

    @Test
    public void testRedisService(){
        redisService.delete(UserRedisKey.userRedisKeyById,"1");
        //redisService.set(UserRedisKey.userRedisKeyById,"1",1);
        //redisService.incr(UserRedisKey.userRedisKeyById,"1");
        //redisService.dec(UserRedisKey.userRedisKeyById,"1");
        System.out.println("结果:"+redisService.get(UserRedisKey.userRedisKeyById,"1"));
    }

}
