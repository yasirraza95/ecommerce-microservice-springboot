package com.springboot.ecommerce.order.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ecommerce.order.enums.Response;
import com.springboot.ecommerce.order.model.Order;
import com.springboot.ecommerce.order.response.ResponseHandler;
import com.springboot.ecommerce.order.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> viewOrderByUserId(@PathVariable("id") String userId) {
		List<Order> result = orderService.viewOrderByUserId(userId);
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCart(@RequestBody Order order) throws Exception {
		Order result = orderService.placeOrder(order);
		return ResponseHandler.generateResponse(Response.TRUE, "Order placed", result, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteOrderById(@PathVariable("id") String id) {
		orderService.deleteById(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Order removed", null, HttpStatus.OK);
	}
}
