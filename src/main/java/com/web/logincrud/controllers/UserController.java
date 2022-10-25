package com.web.logincrud.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
    
    @GetMapping("/list")
    public String showIndex(Model m) {
        List<UserModel> users = service.listUser();
        m.addAttribute("usuarios", users);
        return "/usuario/index";
    }
    
    @GetMapping("/detail/{id}")
    public String showDetail(@PathVariable Integer id, Model m) {
        UserModel user = service.getById(id).get();
        m.addAttribute("user", user);
        return "/usuario/detail";
    }
    
    
    
	@GetMapping("/register")
	public String showRegister() {
		return "/usuario/new";
	}
	
	
	@PostMapping("/newregister")
    public String register(String nombre, String apellidos, String email, String telefono,String password, String[] roles) {        
        UserModel user = new UserModel();
        user.setFirst_name(nombre);
        user.setLast_name(apellidos);
        user.setEmail(email);
        user.setPhone(telefono);
        user.setPassword(passwordEncoder.encode(password));
        
        Set<RolModel> lRol = new HashSet<>();
        
        for (String item : roles) {
            RolModel rol = rservice.getByDescription(item).get();
            lRol.add(rol);
        }
        
        user.setRoles(lRol);
        service.save(user);
        
        return "redirect:/user/list";
    }
	
	@GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id, Model m) {
        UserModel user = service.getById(id).get();
        boolean r1 = user.getRoles().contains(rservice.getByDescription("ROLE_ADMIN").get());
        boolean r2 = user.getRoles().contains(rservice.getByDescription("ROLE_SELLER").get());
        m.addAttribute("aux1", r1);
        m.addAttribute("aux2", r2);
        m.addAttribute("user", user);
        return "/usuario/edit";
    }
	
	@PostMapping("/post/edit/{id}")
	public String edit(@PathVariable Integer id, String nombre, String apellidos, String email, String telefono,String password, String[] roles) {
	    //service.deleteRoles(id);
	    
	    UserModel user = service.getById(id).get();
	    user.setId(id);
	    user.setFirst_name(nombre);
	    user.setLast_name(apellidos);
	    user.setEmail(email);
	    user.setPhone(telefono);
	    user.setPassword(password);
	    
	    Set<RolModel> lRol = new HashSet<>();
	    
	    for (String item : roles) {
            RolModel rol = rservice.getByDescription(item).get();
            lRol.add(rol);
        }
	    
	    user.setRoles(lRol);
	    service.save(user);
	    
	    return "redirect:/user/list";
	}
	
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
	    service.deleteUser(id);
	    return "redirect:/user/list";
	}
}
