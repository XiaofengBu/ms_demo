package com.xiaofeng.ms.controller;

import com.xiaofeng.ms.model.User;
import com.xiaofeng.ms.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sample")
public class SampleController {

    @RequestMapping("/thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name","xiaofeng");
        return "hello";
    }

    @RequestMapping("/user")
    @ResponseBody
    public Result<User> getUser(User user){
        return Result.success(user);
    }
}
