package com.web.logincrud.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.web.logincrud.model.RolModel;
import com.web.logincrud.model.UserModel;
import com.web.logincrud.service.RolService;
import com.web.logincrud.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
    @Autowired
    UserService service;
    
    @Autowired
    RolService rservice;
    
    @Autowired
    PasswordEncoder passwordEncoder;
    
	@GetMapping("/register")
	public String showRegister() {
		return "register";
	}
	
	@PostMapping("/newregister")
	public ModelAndView register(String email, String password) {
	    ModelAndView m = new ModelAndView();
	    if (email.isBlank()) {
            m.setViewName("/register");
            m.addObject("error", "El email es requerido!");
            return m;
        }
	    if (password.isBlank()) {
	        m.setViewName("/register");
            m.addObject("error", "La contraseña es requerida!");
            return m;
        }
	    UserModel user = new UserModel();
	    user.setEmail(email);
	    user.setPassword(passwordEncoder.encode(password));	    
	    RolModel rol = rservice.getByDescription("ROLE_USER").get();
	    Set<RolModel> roles = new HashSet<>();
	    roles.add(rol);
	    user.setRoles(roles);
	    service.save(user);
	    
	    m.setViewName("/index");
	    return m;
	}
}
