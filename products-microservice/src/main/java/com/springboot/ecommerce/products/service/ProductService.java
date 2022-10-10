package com.springboot.ecommerce.products.service;

import java.util.Map;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;

import com.springboot.ecommerce.products.model.Product;
import com.springboot.ecommerce.products.model.ProductEntity;
import com.springboot.ecommerce.products.model.UploadProduct;

@Validated
public interface ProductService {

	public Product getProductById(String id);
	public Map<String, Object> getAllProducts(int page, int size, String subCatName, String name, String price, String sort);
	public Product update(String id, ProductEntity product);
	public void deleteById(String id);
	public Product save(UploadProduct product, MultipartFile file) throws Exception;
	
}
