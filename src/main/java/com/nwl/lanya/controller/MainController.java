package com.nwl.lanya.controller;

import com.nwl.lanya.dto.UserDto;
import com.nwl.lanya.service.UserService;
import com.nwl.lanya.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @Autowired
    private UserServiceImpl userService;

    @RequestMapping("/")
    public String index() {
        return "html/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "html/login";
    }

    @RequestMapping("/perfect")
    public String perfect() {
        return "html/perfect";
    }

    @RequestMapping("logOn")
    public String logOn(UserDto dto){
        int flag = userService.logOn(dto);
        if ( flag == 3){
            return "manage/index";
        }else if (flag == 2){
            return "redirect:/logOnActivationFail";
        }else {
            return "redirect:/logOnFail";
        }
    }

    @RequestMapping("register")
    public String register(UserDto dto) {
        boolean byEmail = userService.findByEmail(dto);
        if (byEmail) {
            return "redirect:/registerFail";
        } else {
            userService.register(dto);
            return "redirect:/registerSuccess";
        }
    }

    @RequestMapping("registerSuccess")
    public String registerSuccess(Model model) {
        model.addAttribute("msgCode", 2);
        model.addAttribute("msg", "注册成功，请登录邮箱激活");
        return "html/login";
    }

    @RequestMapping("registerFail")
    public String registerFail(Model model) {
        model.addAttribute("msgCode", 2);
        model.addAttribute("msg", "该邮箱已被注册");
        return "html/login";
    }

    @RequestMapping("logOnFail")
    public String logOnFail(Model model) {
        model.addAttribute("msgCode", 2);
        model.addAttribute("msg", "请检查邮箱密码正确性");
        return "html/login";
    }

    @RequestMapping("logOnActivationFail")
    public String logOnActivationFail(Model model) {
        model.addAttribute("msgCode", 2);
        model.addAttribute("msg", "邮箱未激活");
        return "html/login";
    }

    @RequestMapping("/activation/{id}")
    public String activation(@PathVariable String id) {
        userService.activation(id);
        return "redirect:/activationSuccess";
    }

    @RequestMapping("/activationSuccess")
    public String activationSuccess(Model model) {
        model.addAttribute("msgCode", 2);
        model.addAttribute("msg", "激活成功，欢迎登录");
        return "html/login";
    }
}
