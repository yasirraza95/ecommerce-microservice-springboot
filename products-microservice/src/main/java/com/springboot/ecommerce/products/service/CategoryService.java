package com.springboot.ecommerce.products.service;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.springboot.ecommerce.products.model.Category;

@Validated
public interface CategoryService {

	public List<Category> getCategories();
	public Category addCategory(Category category);
	public Category updateCategory(String id, Category category);
	public void deleteById(String id);
}