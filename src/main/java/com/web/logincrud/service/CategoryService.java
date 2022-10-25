package com.web.logincrud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.web.logincrud.model.CategoryModel;
import com.web.logincrud.repositories.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository repository;
    
    public List<CategoryModel> allCategories() {
        return repository.findAll();
    }
    
    public CategoryModel getById(Integer id) {
        return repository.findById(id).get();
    }
}
