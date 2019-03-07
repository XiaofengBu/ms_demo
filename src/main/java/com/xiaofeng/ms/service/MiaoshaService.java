package com.xiaofeng.ms.service;

import com.xiaofeng.ms.model.Order;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class MiaoshaService {
	
	@Autowired
	GoodsService goodsService;
	
	@Autowired
	OrderService orderService;

	@Transactional
	public Order miaosha(User user, MsGoodVO goods) {
		//减库存 下订单 写入秒杀订单
		goodsService.reduceStock(goods);
		//order_info maiosha_order
		return orderService.createOrder(user, goods);
	}
	
}
