package com.xiaofeng.ms.dao;

import com.xiaofeng.ms.model.MsOrder;
import com.xiaofeng.ms.model.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;

@Mapper
public interface OrderMapper {
	
	@Select("select * from ms_order where user_id=#{userId} and goods_id=#{goodsId}")
	public MsOrder getMiaoshaOrderByUserIdGoodsId(@Param("userId") long userId, @Param("goodsId") long goodsId);

	@Insert("insert into `order` (user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
			+ "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel}, #{status}, #{createDate} )")
	@SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
	public long insert(Order order);
	
	@Insert("insert into ms_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
	public int insertMiaoshaOrder(MsOrder msOrder);

	
}
