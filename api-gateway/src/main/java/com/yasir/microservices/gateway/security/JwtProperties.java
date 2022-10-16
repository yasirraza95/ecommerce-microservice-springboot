package com.yasir.microservices.gateway.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@ConfigurationProperties(prefix = "jwt")
@Data
@Component
public class JwtProperties {

	private String secretKey = "rzxlszyykpbgqcflzxsqcysyhljt";

	// validity in milliseconds
	private long validityInMs = 3600000; // 1h

}
