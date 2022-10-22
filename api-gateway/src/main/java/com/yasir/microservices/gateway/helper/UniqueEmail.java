package com.yasir.microservices.gateway.helper;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.yasir.microservices.gateway.helper.UniqueEmailValidator;

@Documented
@Target({ ElementType.METHOD, ElementType.FIELD })
@Constraint(validatedBy = UniqueEmailValidator.class)
@Retention(RUNTIME)
public @interface UniqueEmail {
	String message();

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}