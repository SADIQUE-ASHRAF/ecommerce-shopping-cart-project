package com.shopping.cart.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.shopping.cart.entity.Category;

public interface CategoryService {

	public Category saveCategory(Category category);
	
	public Boolean existCategory(String name);
	
	public List<Category> getAllCategory();

	public Category getCategoryById(Integer id);

	public Boolean deleteCategory(int id);
	
	public List<Category> getAllActiveCategory();

	public Page<Category> getAllCategorPagination(Integer pageNo, Integer pageSize);
}
