package com.xiaofeng.ms.model;

import lombok.Data;

import java.util.Date;

@Data
public class MsGoods {
	private Long id;
	private Long goodsId;
	private Integer stockCount;
	private Date startDate;
	private Date endDate;
}
