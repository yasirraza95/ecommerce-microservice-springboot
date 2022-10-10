package com.springboot.ecommerce.products.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import com.springboot.ecommerce.products.enums.Response;
import com.springboot.ecommerce.products.model.Product;
import com.springboot.ecommerce.products.model.ProductEntity;
import com.springboot.ecommerce.products.model.UploadProduct;
import com.springboot.ecommerce.products.response.ResponseHandler;
import com.springboot.ecommerce.products.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/")
	public ResponseEntity<Object> getProducts(@RequestParam(required = false) String name,
			@RequestParam(required = false) String subCatName, @RequestParam(required = false) String price,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = "id.desc") String sort) {

		Map<String, Object> response = productService.getAllProducts(page, size, subCatName, name, price, sort);
		return ResponseHandler.generateResponse(Response.TRUE, "Retrieved all products", response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> getProductById(@PathVariable("id") String id) {
		Product response = productService.getProductById(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product retrieved", response, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addProductImg(@ModelAttribute UploadProduct product,
			@RequestParam("file") MultipartFile file) throws Exception {

		Product result = productService.save(product, file);
		return ResponseHandler.generateResponse(Response.TRUE, "Product added", result, HttpStatus.CREATED);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody ProductEntity product) {
		Product result = productService.update(id, product);
		return ResponseHandler.generateResponse(Response.TRUE, "Product updated", result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable String id) {
		productService.deleteById(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product removed", null, HttpStatus.OK);
	}
}
