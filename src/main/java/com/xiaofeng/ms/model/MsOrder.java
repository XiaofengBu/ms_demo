package com.xiaofeng.ms.model;

import lombok.Data;

@Data
public class MsOrder {
	private Long id;
	private Long userId;
	private Long  orderId;
	private Long goodsId;
}
