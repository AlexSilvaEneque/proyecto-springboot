package com.web.logincrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.logincrud.model.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, Integer> {

}
