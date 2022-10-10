package com.springboot.ecommerce.order.controller;

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
import com.springboot.ecommerce.order.enums.Response;
import com.springboot.ecommerce.order.model.Cart;
import com.springboot.ecommerce.order.response.ResponseHandler;
import com.springboot.ecommerce.order.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getCart(@PathVariable("id") String userId) {
		List<Cart> result = cartService.viewCart(userId);
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCart(@RequestBody Cart cart) {
		Cart result = cartService.addCart(cart);
		return ResponseHandler.generateResponse(Response.TRUE, "Product added", result, HttpStatus.OK);
	}

	@PutMapping("/addQuantity/{id}")
	public ResponseEntity<Object> addQuantity(@PathVariable("id") String id) {
		Cart result = cartService.addQuantity(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Quantity increased", result, HttpStatus.OK);
	}

	@PutMapping("/minusQuantity/{id}")
	public ResponseEntity<Object> minusQuantity(@PathVariable("id") String id) throws Exception {
		Cart result = cartService.minusQuantity(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Quantity decreased", result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteCartById(@PathVariable String id) {
		cartService.deleteCart(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product removed", null, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll/{id}")
	public ResponseEntity<Object> deleteAllCartById(@PathVariable String id) {
		cartService.deleteAllCart(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product removed", null, HttpStatus.OK);
	}
}
