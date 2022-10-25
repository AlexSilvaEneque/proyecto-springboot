package com.web.logincrud.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.web.logincrud.model.CategoryModel;
import com.web.logincrud.model.ProductModel;
import com.web.logincrud.service.CategoryService;
import com.web.logincrud.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductService service;
	@Autowired
	CategoryService cservice;
	
	@GetMapping("/list")
	public String listProduct(Model m) { 
		List<ProductModel> products = service.list();
		m.addAttribute("products", products);
		return "/product/list";
	}
	
	//@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/details/{id}")
	public String getById(@PathVariable Integer id ,Model m) {
		ProductModel product = service.byId(id);
		m.addAttribute("product", product);
		return "/product/detail";
	}
	
	@GetMapping("/new")
	public String showCreate(Model m) {
	    List<CategoryModel> categories = cservice.allCategories();
	    m.addAttribute("categories", categories);
        return "/product/new";
    }
	
	
	@PostMapping("/new/post")
    public String create(String nombre, String precio,Integer stock, Integer category) {
	    if (nombre.isBlank()) {
            return "redirect:/product/new?error1";
        }
	    
	    if(precio.isBlank()) {
	        return "redirect:/product/new?error2";
	    }
	    Double xprecio = Double.parseDouble(precio);
	    
	    ProductModel p = new ProductModel();
	    p.setName(nombre);
	    p.setPrice(xprecio);
	    p.setStock(stock);
	    p.setCategory(cservice.getById(category));
	    service.save(p);
	    
        return "redirect:/product/list";
    }
	
	@GetMapping("/edit/{id}")
    public String showEdit(@PathVariable Integer id, Model m) {
	    ProductModel product = service.byId(id);
	    List<CategoryModel> categories = cservice.allCategories();
        m.addAttribute("categories", categories);
	    m.addAttribute("product", product);
        return "/product/edit";
    }
	
   @PostMapping("/edit/post/{id}")
    public String create(@PathVariable Integer id, String nombre, String precio, Integer stock, Integer category) {
       //FIXME acomodar
        if (nombre.isBlank()) {
            return "redirect:/product/new?error2";
        }
        //FIXME acomodar
        if(precio.isBlank()) {
            return "redirect:/product/new?error2";
        }
        Double xprecio = Double.parseDouble(precio);
        
        ProductModel p = service.byId(id);
        p.setId(id);
        p.setName(nombre);
        p.setStock(stock);
        p.setCategory(cservice.getById(category));
        p.setPrice(xprecio);
        service.save(p);
        
        return "redirect:/product/list";
    }
   
    @GetMapping("/delete/{id}")
    public String create(@PathVariable Integer id) {
       service.delete(id);
       return "redirect:/product/list";
   }
}
