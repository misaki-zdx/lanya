package com.nwl.lanya.controller;

import com.nwl.lanya.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author misaki
 */
@Controller
@RequestMapping("manage")
public class ManageController {


    @RequestMapping("/")
    public String index() {
        return "manage/index";
    }

    @RequestMapping("/addManage")
    public String addMange() {
        return "manage/addManage";
    }

    @RequestMapping("/courseManage")
    public String courseManage() {
        return "manage/courseManage";
    }

    @RequestMapping("/labelManage")
    public String labelManage() {
        return "manage/labelManage";
    }

    @RequestMapping("/userManage")
    public String userManage() {
        return "manage/userManage";
    }

}
