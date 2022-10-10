package com.springboot.ecommerce.order.service;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class ProductFallbackFactory implements FallbackFactory<ProductServiceClient> {

	@Override
	public ProductServiceClient create(Throwable cause) {
		return new ProductServiceClientFallback(cause);
	}

}
