package com.xiaofeng.ms.service;

import com.sun.deploy.net.proxy.pac.PACFunctions;
import com.sun.org.apache.bcel.internal.classfile.Code;
import com.xiaofeng.ms.dao.UserMapper;
import com.xiaofeng.ms.exception.GlobalException;
import com.xiaofeng.ms.exception.LoginException;
import com.xiaofeng.ms.exception.MobileNotExistException;
import com.xiaofeng.ms.exception.ServerException;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.redis.UserRedisKey;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.util.MD5Util;
import com.xiaofeng.ms.util.UUIDUtil;
import com.xiaofeng.ms.vo.LoginVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    public static String TOKEN_COOKIE_NAME = "token";

    public User getUserById(Long id){
        return userMapper.getUserById(id);
    }

    public String login(LoginVO loginVO, HttpServletResponse response) throws GlobalException{
        if(loginVO == null || loginVO.getPassword() == null || loginVO.getMobile() == null){
            throw new ServerException();
        }
        String moblie = loginVO.getMobile();
        String password = loginVO.getPassword();
        User user = getUserById(Long.parseLong(moblie));
        if(user == null){
            throw new MobileNotExistException();
        }
        String dbPass = user.getPassword();
        String dbSalt = user.getSalt();
        String calcPass = MD5Util.tranPass2DBPass(password,dbSalt);
        if(dbPass.equals(calcPass)){
            String token = UUIDUtil.uuid();
            addUserToSession(response,token,user);
            return token;
        }else{
            throw new LoginException();
        }
    }
    private void addUserToSession(HttpServletResponse response,String token, User user){
        redisService.set(UserRedisKey.token,token,user);
        Cookie cookie = new Cookie(TOKEN_COOKIE_NAME,token);
        cookie.setPath("/");
        cookie.setMaxAge(UserRedisKey.TOKEN_EXPIRE);
        response.addCookie(cookie);
    }
    public User getUserByToken(HttpServletResponse response,String token){
        if(StringUtils.isEmpty(token)){
            return null;
        }else{
           User user = redisService.get(UserRedisKey.token,token,User.class);
           if(user!=null){
               addUserToSession(response,token,user);
           }
           return user;
        }

    }

}
