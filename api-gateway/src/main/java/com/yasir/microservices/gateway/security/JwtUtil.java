package com.yasir.microservices.gateway.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtUtil {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.token.validity}")
	private long tokenValidity;

	public Claims getClaims(final String token) {
		try {
			Claims body = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			return body;
		} catch (Exception e) {
			System.out.println(e.getMessage() + " => " + e);
		}
		return null;
	}


//	public String generateAccessToken(User user) {
//		long nowMillis = System.currentTimeMillis();
//		long expMillis = nowMillis + tokenValidity;
//		Date exp = new Date(expMillis);
//		
//		return Jwts.builder()
//				.setSubject(user.getId() + "," +user.getEmail())
//				.claim("roles", user.getRoles().toString())
//				.setIssuer("Ecommerce")
//				.setIssuedAt(new Date())
//				.setExpiration(exp)
//				.signWith(SignatureAlgorithm.HS512, jwtSecret)
//				.compact();
//	}
	
	public boolean validateAccessToken(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch (SignatureException ex) {
			System.out.println("JWT Signature not valid");
		} catch (MalformedJwtException ex) {
			System.out.println("JWT is invalid");
		} catch (ExpiredJwtException ex) {
			System.out.println("JWT is expired");
		} catch (UnsupportedJwtException ex) {
			System.out.println("JWT is not supported");
		} catch (IllegalArgumentException ex) {
			System.out.println("JWT Claim is missing");
		}
		return false;
	}
	
	public String getSubject(String token) {
		return parseClaims(token).getSubject();
	}
	
	public Claims parseClaims(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
	}

}