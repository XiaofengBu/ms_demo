package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.result.Result;
import com.xiaofeng.ms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sample")
public class SampleController {
    @Autowired
    private UserService userService;

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","xiaofeng");
        return "hello";
    }

    /**
     * 测试数据库
     * @return
     */
    @RequestMapping("/getUser")
    @ResponseBody
    public Result<User> getUser(){
        User user = userService.getUserById(1);
        return Result.success(user);
    }

    /**
     * 测试事务
     * @return
     */
    @RequestMapping("/getTran")
    @ResponseBody
    public Result<Integer> getTran(){
        userService.getTran();
        return Result.success(1);
    }
}
