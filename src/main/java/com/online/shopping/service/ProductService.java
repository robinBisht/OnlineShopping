package com.online.shopping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Product;
import com.online.shopping.repository.ProductRepository;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepo;
	
	public List<Product> viewAllProducts(){
		return productRepo.findAll();
	}
	
	public void addProduct(Product product) {
		productRepo.save(product); 
	}
	
	public Product viewProduct(Integer id) {
		return productRepo.getById(id);
	}
	
	public void removeProduct(Integer id) {
		productRepo.deleteById(id);
	}
	
	public Product updateProduct(Product product) {
		return productRepo.save(product);
	}
	public List<Product> viewProductByCategory(String categoryName){
		List<Product> listAll = viewAllProducts();
		return listAll.stream().filter( product -> product.getCategory().getCategoryName().equals(categoryName) ).collect(Collectors.toList());
	}
	
}
