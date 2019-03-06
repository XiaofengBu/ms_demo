package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.service.GoodsService;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/goods")
public class GoodsController {

	@Autowired
	UserService userService;
	@Autowired
	RedisService redisService;
	@Autowired
	GoodsService goodsService;
	
    @RequestMapping("/to_list")
    public String list(Model model, User user) {
    	model.addAttribute("user", user);
		List<MsGoodVO> goodsList = goodsService.getMsGoodList();
		model.addAttribute("goodsList", goodsList);
        return "goodsList";
    }


	@RequestMapping("/to_detail/{goodsId}")
	public String detail(Model model,User user,
						 @PathVariable("goodsId")long goodsId) {
		model.addAttribute("user", user);

		MsGoodVO goods = goodsService.getGoodsVoByGoodsId(goodsId);
		model.addAttribute("goods", goods);

		long startAt = goods.getStartDate().getTime();
		long endAt = goods.getEndDate().getTime();
		long now = System.currentTimeMillis();

		int miaoshaStatus = 0;
		int remainSeconds = 0;
		if(now < startAt ) {//秒杀还没开始，倒计时
			miaoshaStatus = 0;
			remainSeconds = (int)((startAt - now )/1000);
		}else  if(now > endAt){//秒杀已经结束
			miaoshaStatus = 2;
			remainSeconds = -1;
		}else {//秒杀进行中
			miaoshaStatus = 1;
			remainSeconds = 0;
		}
		model.addAttribute("miaoshaStatus", miaoshaStatus);
		model.addAttribute("remainSeconds", remainSeconds);
		return "goods_detail";
	}
    
}
