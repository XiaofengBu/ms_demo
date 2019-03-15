package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.redis.GoodsKey;
import com.xiaofeng.ms.redis.RedisService;
import com.xiaofeng.ms.result.Result;
import com.xiaofeng.ms.service.GoodsService;
import com.xiaofeng.ms.service.UserService;
import com.xiaofeng.ms.vo.GoodsDetailVo;
import com.xiaofeng.ms.vo.MsGoodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

	@Autowired
	ThymeleafViewResolver thymeleafViewResolver;

	@Autowired
	ApplicationContext applicationContext;

	@RequestMapping(value="/to_list", produces="text/html")
	@ResponseBody
    public String list(Model model, User user, HttpServletRequest request, HttpServletResponse response) {
    	model.addAttribute("user", user);
		String html = redisService.get(GoodsKey.getGoodsList, "goods", String.class);
		if(!StringUtils.isEmpty(html)) {
			return html;
		}
		List<MsGoodVO> goodsList = goodsService.getMsGoodList();
		model.addAttribute("goodsList", goodsList);
		WebContext ctx = new WebContext(request,response,
				request.getServletContext(),request.getLocale(),model.asMap());
		//手动渲染
		html = thymeleafViewResolver.getTemplateEngine().process("goodsList", ctx);
		if(!StringUtils.isEmpty(html)) {
			redisService.set(GoodsKey.getGoodsList, "goods", html);
		}
		return html;
    }


	@RequestMapping(value="/to_detail/{goodsId}")
	@ResponseBody
	public Result<GoodsDetailVo> detail(Model model,User user,
						 @PathVariable("goodsId")long goodsId,HttpServletRequest request, HttpServletResponse response) {
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
        GoodsDetailVo vo = new GoodsDetailVo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
		return Result.success(vo);
	}

    @RequestMapping(value="/to_detail2/{goodsId}", produces="text/html")
    @ResponseBody
    public String detail2(Model model,User user,
                         @PathVariable("goodsId")long goodsId,HttpServletRequest request, HttpServletResponse response) {
        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, ""+goodsId, String.class);
        if(!StringUtils.isEmpty(html)) {
            return html;
        }
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
        WebContext ctx = new WebContext(request,response,
                request.getServletContext(),request.getLocale(),model.asMap());
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if(!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, ""+goodsId, html);
        }
        return html;
    }
    
}
