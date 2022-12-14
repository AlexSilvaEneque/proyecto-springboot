package com.web.logincrud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.web.logincrud.model.ProductModel;

@Repository
public interface ProductRepository extends JpaRepository<ProductModel, Integer> {
	boolean existsByName(String name);
}
