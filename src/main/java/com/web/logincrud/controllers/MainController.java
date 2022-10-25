package com.web.logincrud.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.logincrud.service.UserService;

@Controller
@RequestMapping("/")
public class MainController {
    @Autowired
    UserService userv;
	
	@GetMapping({"/", "/login"})
	public String login() {
		return "login";
	}
	
	@GetMapping("/index")
	public String showIndex(Principal p, Model m) {
//	    UserModel userCurrent = userv.getByEmail(p.getName()).get();
//	    m.addAttribute("nombreu", userCurrent.getFirst_name());
		return "index";
	}
	
	@GetMapping("/forbidden")
	public String showForbidden() {
		return "forbidden";
	}
}