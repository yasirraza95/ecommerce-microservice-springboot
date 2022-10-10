package com.springboot.ecommerce.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.ecommerce.user.model.Mail;
import com.springboot.ecommerce.user.service.MailService;

@RestController
public class MailController {

	@Autowired
	private MailService mailService;
	
	@GetMapping("/")
	public void sendEmail() {
		Mail mail = new Mail();
		mail.setMailFrom("raza.yasir95@gmail.com");
		mail.setMailTo("raza.yasir95@gmail.com");
		mail.setMailSubject("Spring Boot - Email demo");
		mail.setMailContent("Just testing");
		mailService.sendEmail(mail);
	}

}
