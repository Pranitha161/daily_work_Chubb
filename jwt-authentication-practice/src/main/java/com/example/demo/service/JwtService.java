package com.example.demo.service;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
	@Value("${jwt.secret}")
	private String secret;
	@Value("${security.jwt.expiration-minutes}")
	private long expirationMinutes;

	public String generateToken(String username, List<String> roles) {
		Instant now = Instant.now();
		return Jwts.builder().setSubject(username).setIssuedAt(Date.from(now))
				.setExpiration(Date.from(now.plus(Duration.ofMinutes(expirationMinutes)))).claim("roles", roles)
				.signWith(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS256)
				.compact();
	}

	public String extractUsername(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8))).build()
				.parseClaimsJws(token).getBody().getSubject();
	}
}
