package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.service.GoodsService;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/toList")
    public String list(Model model, User user) {
    	model.addAttribute("user", user);
		List<MsGoodVO> goodsList = goodsService.getMsGoodList();
		model.addAttribute("goodsList", goodsList);
        return "goodsList";
    }
    
}
