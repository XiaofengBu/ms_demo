package com.xiaofeng.ms.dao;


import com.xiaofeng.ms.vo.MsGoodVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsMapper {
    @Select("SELECT MSG.*,G.* FROM MS_GOODS MSG LEFT JOIN GOODS G ON MSG.GOODS_ID = G.ID")
    List<MsGoodVO> getMsGoodsList();
}
