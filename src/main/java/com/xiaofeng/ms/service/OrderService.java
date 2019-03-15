package com.xiaofeng.ms.service;

import java.util.Date;

import com.sun.org.apache.xpath.internal.operations.Or;
import com.xiaofeng.ms.dao.OrderMapper;
import com.xiaofeng.ms.model.MsOrder;
import com.xiaofeng.ms.model.Order;
import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class OrderService {
	
	@Autowired
	OrderMapper orderMapper;
	
	public MsOrder getMiaoshaOrderByUserIdGoodsId(long userId, long goodsId) {
		return orderMapper.getMiaoshaOrderByUserIdGoodsId(userId, goodsId);
	}

	public Order getOrderById(long orderId) {
		return orderMapper.getOrderById(orderId);
	}
	@Transactional
	public Order createOrder(User user, MsGoodVO goods) {
		Order orderInfo = new Order();
		orderInfo.setCreateDate(new Date());
		orderInfo.setDeliveryAddrId(0L);
		orderInfo.setGoodsCount(1);
		orderInfo.setGoodsId(goods.getId());
		orderInfo.setGoodsName(goods.getGoodsName());
		orderInfo.setGoodsPrice(goods.getMsPrice());
		orderInfo.setOrderChannel(1);
		orderInfo.setStatus(0);
		orderInfo.setUserId(user.getId());
		long orderId = orderMapper.insert(orderInfo);
		MsOrder miaoshaOrder = new MsOrder();
		miaoshaOrder.setGoodsId(goods.getId());
		miaoshaOrder.setOrderId(orderId);
		miaoshaOrder.setUserId(user.getId());
		orderMapper.insertMiaoshaOrder(miaoshaOrder);
		return orderInfo;
	}
	
}
