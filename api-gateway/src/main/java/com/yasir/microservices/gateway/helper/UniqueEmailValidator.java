package com.yasir.microservices.gateway.helper;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yasir.microservices.gateway.helper.UniqueEmail;
import com.yasir.microservices.gateway.repository.UserRepository;

@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {

	@Autowired
	UserRepository userRepo;

	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		return userRepo.findByEmail(email) != null;
	}
}