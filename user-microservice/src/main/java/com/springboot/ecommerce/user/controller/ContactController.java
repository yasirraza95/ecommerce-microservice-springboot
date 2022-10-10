package com.springboot.ecommerce.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.ecommerce.user.enums.Response;
import com.springboot.ecommerce.user.model.Contact;
import com.springboot.ecommerce.user.response.ResponseHandler;
import com.springboot.ecommerce.user.service.ContactService;

@RestController
@RequestMapping("/user/")
public class ContactController {

	@Autowired
	ContactService contactService;

	@PostMapping("contact")
	public ResponseEntity<Object> contact(@RequestBody Contact contact) {
		contactService.sendEmail(contact.getEmail(), contact.getSubject(), contact.getMessage());
		return ResponseHandler.generateResponse(Response.TRUE, "Message sent!", contact, HttpStatus.OK);
	}
}
