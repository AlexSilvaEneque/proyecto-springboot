package com.web.logincrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.logincrud.model.UserModel;
import com.web.logincrud.repositories.UserRepository;

@Service
public class UserService {
	@Autowired
	UserRepository repository;
	
	public List<UserModel> listUser() {
		return repository.findAll();
	}
	
	public Optional<UserModel> getById(Integer id) {
		return repository.findById(id);
	}
	
	public Optional<UserModel> getByEmail(String email) {
		return repository.findByEmail(email);
	}
	
	public void save(UserModel user) {
		repository.save(user);
	}
	
	public boolean existsById(Integer id) {
		return repository.existsById(id);
	}
	
	public boolean existsByEmail(String email) {
		return repository.existsByEmail(email);
	}
	
	public void deleteUser(Integer id) {
	    repository.deleteById(id);
	}
}
