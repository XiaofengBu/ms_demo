package com.xiaofeng.ms.service;

import com.xiaofeng.ms.dao.GoodsMapper;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;
    public List<MsGoodVO> getMsGoodList(){
        return goodsMapper.getMsGoodsList();
    }
}
