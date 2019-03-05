package com.xiaofeng.ms.vo;

import com.xiaofeng.ms.model.Goods;
import lombok.Data;

import java.util.Date;

@Data
public class MsGoodVO extends Goods {
    private Double msPrice;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
}
