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
import com.springboot.ecommerce.order.model.Wishlist;
import com.springboot.ecommerce.order.response.ResponseHandler;
import com.springboot.ecommerce.order.service.WishlistService;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {

	@Autowired
	private WishlistService wishlistService;

	@GetMapping("/{id}")
	public ResponseEntity<Object> getWishlist(@PathVariable("id") String userId) {
		List<Wishlist> result = wishlistService.viewWishlist(userId);
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Object> addCart(@RequestBody Wishlist wishlist) throws Exception {
		Wishlist result = wishlistService.addWishlist(wishlist);
		return ResponseHandler.generateResponse(Response.TRUE, "Product added", result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> deleteWishlistById(@PathVariable String id) {
		wishlistService.deleteWishlist(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product removed", null, HttpStatus.OK);
	}

	@DeleteMapping("/deleteAll/{id}")
	public ResponseEntity<Object> deleteAllWishlistById(@PathVariable String id) {
		wishlistService.deleteAllWishlist(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Product removed", null, HttpStatus.OK);
	}

}
