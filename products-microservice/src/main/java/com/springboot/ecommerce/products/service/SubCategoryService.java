package com.springboot.ecommerce.products.service;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import com.springboot.ecommerce.products.model.Subcategory;

@Validated
public interface SubCategoryService {

	public List<Subcategory> getSubCategories();
	public Subcategory addSubCategory(Subcategory subCategory);
	public Subcategory updateSubCategory(String id, Subcategory subCategory);
	public void deleteById(String id);
}