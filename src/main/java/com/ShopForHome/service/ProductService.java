package com.ShopForHome.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ShopForHome.entity.Product;
import com.ShopForHome.repository.ProductRepository;
import com.ShopForHome.request.CreateProductRequest;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public Product createProduct(CreateProductRequest request) {
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		product.setImage(request.getImage());
		product.setCategory(request.getCategory());
		return productRepository.save(product);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Product updateProduct(long id, CreateProductRequest request) {
		Optional<Product> productResult = productRepository.findById(id);
		if (productResult.isEmpty()) {
			throw new UsernameNotFoundException("Could not find Product");
		}
		
		Product product = productResult.get();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStock(request.getStock());
		product.setImage(request.getImage());
		product.setCategory(request.getCategory());
		return productRepository.save(product);
	}
	
	public Product getProduct(long id) {
		Optional<Product> productResult = productRepository.findById(id);
		if (productResult.isEmpty()) {
			throw new UsernameNotFoundException("Could not find Product");
		}
		return productResult.get();
	}
	
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
	}
}
