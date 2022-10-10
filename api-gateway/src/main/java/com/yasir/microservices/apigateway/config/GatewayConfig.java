package com.yasir.microservices.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

	@Bean
	public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
		return builder.routes().route("user-service", r -> r.path("/user/**").uri("lb://USER-MICROSERVICE"))
				.route("product-service", r -> r.path("/product/**").uri("lb://PRODUCT-MICROSERVICE"))
				.route("product-service2", r -> r.path("/category/**").uri("lb://PRODUCT-MICROSERVICE"))
				.route("product-service3", r -> r.path("/subcategory/**").uri("lb://PRODUCT-MICROSERVICE"))
				.route("order-service", r -> r.path("/cart/**").uri("lb://ORDER-MICROSERVICE"))
				.route("order-service2", r -> r.path("/wishlist/**").uri("lb://ORDER-MICROSERVICE"))
				.route("order-service3", r -> r.path("/order/**").uri("lb://ORDER-MICROSERVICE")).build();
	}
}
