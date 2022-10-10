package com.springboot.ecommerce.user.controller;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ecommerce.user.enums.Response;
import com.springboot.ecommerce.user.model.User;
import com.springboot.ecommerce.user.response.ResponseHandler;
import com.springboot.ecommerce.user.service.UserService;

@RestController
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("login")
	public ResponseEntity<Object> login(@RequestBody User user) {
		String token = userService.login(user);
		return ResponseHandler.generateResponse(Response.TRUE, token, null, HttpStatus.OK);
	}

	@PostMapping("signup")
	public ResponseEntity<Object> signup(@Valid @RequestBody User user) {
		User result = userService.signup(user);
		return ResponseHandler.generateResponse(Response.TRUE, "User added successfully!", result, HttpStatus.OK);
	}

	@GetMapping("profile/{id}")
	public ResponseEntity<Object> getProfile(@PathVariable final String id) {
		User result = userService.getProfile(id);
		return ResponseHandler.generateResponse(Response.TRUE, "Successfully retrieved data!", result, HttpStatus.OK);
	}

	@PutMapping("profile/{id}")
	public ResponseEntity<Object> updateProfile(@PathVariable String id, @RequestBody User user) {
		User result = userService.updateProfile(id, user);
		return ResponseHandler.generateResponse(Response.TRUE, "Profile updated successfully", result, HttpStatus.OK);
	}

	@PutMapping("activation/{username}")
	public ResponseEntity<Object> activateProfile(@PathVariable String username) {
		userService.activateProfile(username);
		return ResponseHandler.generateResponse(Response.TRUE, "Profile activated", null, HttpStatus.OK);
	}

	@PostMapping("forgotPassword")
	public ResponseEntity<Object> forgotPassword(@RequestBody User user) {
		userService.forgotPassword(user);
		return ResponseHandler.generateResponse(Response.TRUE, "An email has been sent. Kindly check your inbox", null,
				HttpStatus.OK);

	}

	@GetMapping("checkToken")
	public ResponseEntity<Object> checkToken(@RequestParam("token") String token) {
		userService.checkToken(token);
		return ResponseHandler.generateResponse(Response.TRUE, "Sorry token expired", null, HttpStatus.OK);
	}

}
