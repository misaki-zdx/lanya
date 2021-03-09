package com.nwl.lanya.controller;

import com.nwl.lanya.dto.UserDto;
import com.nwl.lanya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

	@Autowired
	private UserService userService;

	@RequestMapping("/")
	public String index(){
		return "html/index";
	}
	
	@RequestMapping("/login")
	public String login(){
		return "html/login";
	}

	@RequestMapping("/perfect")
	public String perfect(){
		return "html/perfect";
	}

	@RequestMapping("register")
	public String register(UserDto dto){
		try {

			boolean byEmail = userService.findByEmail(dto);
			if (byEmail){
				return "Redirect:/registerFail";
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return "";
	}

	@RequestMapping("registerFail")
	public String registerFail(Model model){
		model.addAttribute("");
		return "";
	}
}
