package com.springboot.ecommerce.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.springboot.ecommerce.order.model.Product;

@FeignClient(name = "PRODUCT-MICROSERVICE", fallbackFactory = ProductFallbackFactory.class)
public interface ProductServiceClient {

	@GetMapping(value = "/product/{id}")
	public Product getProduct(@PathVariable("id") int id);
}
