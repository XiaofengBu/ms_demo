package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
    @RequestMapping("/to_list")
    public String list(Model model, User user) {
    	model.addAttribute("user", user);
        return "goods_list";
    }
    
}