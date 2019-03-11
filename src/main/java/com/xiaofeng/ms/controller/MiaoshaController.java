package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.MsOrder;
import com.xiaofeng.ms.model.Order;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.service.GoodsService;
import com.xiaofeng.ms.service.MiaoshaService;
import com.xiaofeng.ms.service.OrderService;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/miaosha")
public class MiaoshaController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	MiaoshaService miaoshaService;

	//800进程 10次 大约1000qps
    @RequestMapping("/do_miaosha")
    public String list(Model model, User user,
					   @RequestParam("goodsId")long goodsId) {
    	model.addAttribute("user", user);
    	if(user == null) {
    		return "login";
    	}
    	//判断库存
    	MsGoodVO goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	int stock = goods.getStockCount();
    	if(stock <= 0) {
    		model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
    		return "miaosha_fail";
    	}
    	//判断是否已经秒杀到了
    	MsOrder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
    	if(order != null) {
    		model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
    		return "miaosha_fail";
    	}
    	//减库存 下订单 写入秒杀订单
    	Order orderInfo = miaoshaService.miaosha(user, goods);
    	model.addAttribute("orderInfo", orderInfo);
    	model.addAttribute("goods", goods);
        return "order_detail";
    }
}
