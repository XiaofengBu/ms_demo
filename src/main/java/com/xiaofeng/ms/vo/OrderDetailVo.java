package com.xiaofeng.ms.vo;

import com.xiaofeng.ms.model.Order;

public class OrderDetailVo {
	private MsGoodVO goods;
	private Order order;
	public MsGoodVO getGoods() {
		return goods;
	}
	public void setGoods(MsGoodVO goods) {
		this.goods = goods;
	}
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
}
