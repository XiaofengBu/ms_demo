package com.xiaofeng.ms.dao;


import com.xiaofeng.ms.model.MsGoods;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("SELECT MSG.stock_count,msg.start_date,msg.end_date,msg.ms_price,G.* FROM MS_GOODS MSG LEFT JOIN GOODS G ON MSG.GOODS_ID = G.ID")
    List<MsGoodVO> getMsGoodsList();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date,mg.ms_price from MS_GOODS mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    MsGoodVO getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update("update ms_goods set stock_count = stock_count - 1 where goods_id = #{goodsId} and stock_count>0")
    public int reduceStock(MsGoods g);
}
