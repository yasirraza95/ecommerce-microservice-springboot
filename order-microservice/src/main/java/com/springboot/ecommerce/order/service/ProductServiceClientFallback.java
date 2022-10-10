package com.springboot.ecommerce.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.springboot.ecommerce.order.model.Product;

import feign.FeignException;

public class ProductServiceClientFallback implements ProductServiceClient {

	Logger logger = LoggerFactory.getLogger(this.getClass());
	private final Throwable cause;

	public ProductServiceClientFallback(Throwable cause) {
		this.cause = cause;
	}

	@Override
	public Product getProduct(int id) {
		if (cause instanceof FeignException && ((FeignException) cause).status() == 404) {
			logger.error("404 error took place when getAlbums was called with userId: " + id + ". Error message: "
					+ cause.getLocalizedMessage());
		} else {
			logger.error("Other error took place: " + cause.getLocalizedMessage());
		}
		return new Product();
	}

}
