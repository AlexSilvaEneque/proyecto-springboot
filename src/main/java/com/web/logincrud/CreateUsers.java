package com.web.logincrud;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.web.logincrud.model.RolModel;
import com.web.logincrud.model.UserModel;
import com.web.logincrud.service.RolService;
import com.web.logincrud.service.UserService;

@Service
public class CreateUsers implements CommandLineRunner {
	@Autowired
	UserService service;
	
	@Autowired
	RolService rservice;
	
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public void run(String... args) throws Exception {
//		UserModel user1 = new UserModel();
//		String passwordE = passwordEncoder.encode("admin");
//		RolModel rolA = rservice.getByDescription("ROLE_ADMIN").get();
//		Set<RolModel> roles = new HashSet<>();
//		roles.add(rolA);
//		user1.setEmail("admin@gmail.com");
//		user1.setPassword(passwordE);
//		user1.setFirst_name("Alex");
//		user1.setLast_name("Silva Eneque");
//		user1.setPhone("976565361");
//		user1.setRoles(roles);
//		service.save(user1);
		
		/*UserModel user2 = new UserModel();
		String passwordE2 = passwordEncoder.encode("user");
		user2.setEmail("user@gmail.com");
		RolModel rolU = rservice.getByDescription("ROLE_USER").get();
		Set<RolModel> roles2 = new HashSet<>();
		roles2.add(rolU);
		user2.setPassword(passwordE2);
		user2.setRoles(roles2);
		service.save(user2);*/
	}
	
	
}
