package com.ShopForHome.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ShopForHome.entity.Product;
import com.ShopForHome.request.CreateProductRequest;
import com.ShopForHome.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping("/create")
	public Product createProduct(@RequestBody CreateProductRequest request) {
		return productService.createProduct(request);
	}
	
	@GetMapping
	public List<Product> getAllProducts(){
		return productService.getAllProducts();
	}
	
	@GetMapping("/{id}")
	public Product getProductById(@PathVariable long id) {
		return productService.getProduct(id);
	}
	
	@DeleteMapping("/{id}")
	public void deleteProductById(@PathVariable long id) {
		productService.deleteProduct(id);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct(@PathVariable long id, @RequestBody CreateProductRequest request) {
		return productService.updateProduct(id, request);
	}
	
}
