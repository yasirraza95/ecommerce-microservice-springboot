package com.springboot.ecommerce.user.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.springboot.ecommerce.user.exception.CustomException;

@Service
public class ContactServiceImpl implements ContactService {

	@Autowired
	private JavaMailSender mailSender;

	// FIXME html template
	@Override
	public String sendEmail(String toEmail, String subject, String body) {
		try {

			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);

			helper.setSubject(subject);
			helper.setFrom("raza.yasir95@gmail.com");
			helper.setTo("raza.yasir95@gmail.com");
			helper.setText("<b>Hey guys</b>,<br><i>Welcome to my new home</i>", true);

			this.mailSender.send(message);
			return "Email sent";
		} catch (MessagingException e) {
//			return e.getMessage();
			throw new CustomException(e.getMessage());
		} catch (Exception e) {
//			return e.getMessage();
			throw new CustomException(e.getMessage());
		}

	}

}
