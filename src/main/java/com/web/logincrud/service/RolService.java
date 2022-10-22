package com.web.logincrud.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.logincrud.model.RolModel;
import com.web.logincrud.repositories.RolRepository;

@Service
public class RolService {
	@Autowired
	RolRepository repository;
	
	public void save(RolModel rol) {
		repository.save(rol);
	}
	
	public Optional<RolModel> getByDescription(String description) {
        return repository.findByDescription(description);
    }
	
	public boolean existsByDescription(String description) {
        return repository.existsByDescription(description);
    }
}
