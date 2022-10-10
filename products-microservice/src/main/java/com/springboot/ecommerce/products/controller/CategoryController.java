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
import com.springboot.ecommerce.products.model.Category;
import com.springboot.ecommerce.products.response.ResponseHandler;
import com.springboot.ecommerce.products.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping("/")
	public ResponseEntity<Object> getCategories() {
		List<Category> result = categoryService.getCategories();
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public void addCategory(@RequestBody Category category) {
		categoryService.addCategory(category);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateCategory(@PathVariable String id, @RequestBody Category category) {
		Category result = categoryService.updateCategory(id, category);
		return ResponseHandler.generateResponse(Response.TRUE, "Product updated", result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCategory(@PathVariable String id) {
		categoryService.deleteById(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Category removed", null, HttpStatus.OK);
	}
}
