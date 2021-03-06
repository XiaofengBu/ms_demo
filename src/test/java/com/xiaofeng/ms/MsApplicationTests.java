package com.xiaofeng.ms;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisConfig;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.redis.UserRedisKey;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.util.MD5Util;
import com.xiaofeng.ms.util.ValidatorUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.Valid;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MsApplicationTests {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisService redisService;

    private Logger logger = LoggerFactory.getLogger(MsApplicationTests.class);
    @Test
    public void contextLoads() {
    }
    @Test
    public void testDb(){
    }

    @Test
    public void testTran(){
        //userService.getTran();
    }
    @Test
    public void testRedis(){
//        String key = UserRedisKey.userRedisKeyById.getRedisKey() + "1";
//        redisTemplate.opsForValue().set(key,"2019年2月28日16:37:51");
//        System.out.println(redisTemplate.opsForValue().get(key));
    }

    @Test
    public void testRedisService(){
        //redisService.delete(UserRedisKey.userRedisKeyById,"1");
        //redisService.set(UserRedisKey.userRedisKeyById,"1",1);
        //redisService.incr(UserRedisKey.userRedisKeyById,"1");
        //redisService.dec(UserRedisKey.userRedisKeyById,"1");
        User user = new User();
        user.setNickname("测试");
        redisService.set(UserRedisKey.token,"123",user);
        User user2 = redisService.get(UserRedisKey.token,"123",User.class);
        System.out.println("结果:"+ user2.getNickname());
    }

    @Test
    public void testMD5(){
        System.out.println("密码:"+MD5Util.inputPass2TranPass("123456"));
    }
    @Test
    public void testIsMoblie(){
        logger.info(String.valueOf(ValidatorUtil.isMobile("12345678")));
    }
}
