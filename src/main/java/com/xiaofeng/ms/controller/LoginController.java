package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.result.Result;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.LoginVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.nio.cs.US_ASCII;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/doLogin")
    @ResponseBody
    public Result<Boolean> doLogin(@Valid LoginVO loginVO, HttpServletResponse response){
        userService.login(loginVO,response);
        return Result.success(true);
    }
}
