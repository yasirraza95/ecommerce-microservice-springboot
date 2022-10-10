package com.springboot.ecommerce.user.service;

public interface ContactService {

	public String sendEmail(String email, String subject, String body);
}
