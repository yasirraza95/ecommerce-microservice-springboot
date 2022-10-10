package com.springboot.ecommerce.products.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.products.enums.Response;
import com.springboot.ecommerce.products.model.Subcategory;
import com.springboot.ecommerce.products.response.ResponseHandler;
import com.springboot.ecommerce.products.service.SubCategoryService;

@RestController
@RequestMapping("/subcategory")
public class SubCategoryController {

	@Autowired
	private SubCategoryService subCategoryService;

	@GetMapping("/")
	public ResponseEntity<Object> getSubCategories() {
		List<Subcategory> result = subCategoryService.getSubCategories();
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PostMapping("/add")
	void addSubCategory(@RequestBody Subcategory subCategory) {
		subCategoryService.addSubCategory(subCategory);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable String id, @RequestBody Subcategory subCategory) {
		Subcategory result = subCategoryService.updateSubCategory(id, subCategory);
		return ResponseHandler.generateResponse(Response.TRUE, "Product updated", result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable String id) {
		subCategoryService.deleteById(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Category removed", null, HttpStatus.OK);
	}
}
