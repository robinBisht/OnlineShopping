package com.online.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.Category;
import com.online.shopping.repository.CategoryRepository;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> findAll(){
		return categoryRepository.findAll();
	}
	public Category findCategoryById(Integer id) {
		return categoryRepository.getById(id);
	}
	public void addCategory(Category category) {
		categoryRepository.save(category);
	}
	public void updateCategory(Category category) {
		categoryRepository.save(category);
	}
	public void deleteCategory(Category category) {
		categoryRepository.delete(category);
	}
}
