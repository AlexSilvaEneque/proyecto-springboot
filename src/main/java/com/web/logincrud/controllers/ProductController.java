package com.web.logincrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.logincrud.model.ProductModel;
import com.web.logincrud.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService service;
	
	@GetMapping("/list")
	public String listProduct(Model m) {
		List<ProductModel> products = service.list();
		m.addAttribute("products", products);
		return "/product/list";
	}
	
	/* */
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/details/{id}")
	public String getById(@PathVariable Integer id ,Model m) {
		ProductModel product = service.byId(id);
		m.addAttribute("product", product);
		return "/product/detail";
	}
}
