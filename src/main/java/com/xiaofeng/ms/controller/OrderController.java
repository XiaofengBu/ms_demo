package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.Order;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.result.CodeMsg;
import com.xiaofeng.ms.result.Result;
import com.xiaofeng.ms.service.GoodsService;
import com.xiaofeng.ms.service.OrderService;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.MsGoodVO;
import com.xiaofeng.ms.vo.OrderDetailVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	UserService userService;
	
	@Autowired
	RedisService redisService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/detail")
    @ResponseBody
    public Result<OrderDetailVo> info(Model model, User user,
									  @RequestParam("orderId") long orderId) {
    	if(user == null) {
    		return Result.error(CodeMsg.SESSION_ERROR);
    	}
    	Order order = orderService.getOrderById(orderId);
    	if(order == null) {
    		return Result.error(CodeMsg.ORDER_NOT_EXIST);
    	}
    	long goodsId = order.getGoodsId();
    	MsGoodVO goods = goodsService.getGoodsVoByGoodsId(goodsId);
    	OrderDetailVo vo = new OrderDetailVo();
    	vo.setOrder(order);
    	vo.setGoods(goods);
    	return Result.success(vo);
    }
    
}
