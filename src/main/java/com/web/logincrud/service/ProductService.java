package com.web.logincrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.logincrud.model.ProductModel;
import com.web.logincrud.repositories.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository repository;
	
	public List<ProductModel> list() {
		return repository.findAll();
	}
	
	public ProductModel byId(Integer id) {
		return repository.findById(id).get();
	}
}
