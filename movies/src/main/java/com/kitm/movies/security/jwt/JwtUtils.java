package com.kitm.movies.security.jwt;

import java.security.Key;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.kitm.movies.security.services.UserDetailsImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Value("${jwt.expiration}")
	private int jwtExpirationMs;

	public String generateJwtToken(Authentication authentication) {
		UserDetailsImpl userPrinciple = (UserDetailsImpl) authentication.getPrincipal();

		return Jwts.builder()
				.setSubject((userPrinciple.getUsername()))
				.setIssuedAt(new Date())
				.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
				.signWith(key(), SignatureAlgorithm.HS256)
				.compact();
	}
	
	private Key key() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
	}

	public String getUserFromJwtToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key())
				.build()
				.parseClaimsJws(token)
				.getBody()
				.getSubject();
	}

	public boolean validateJwtToken(String authToken) {
		try {
			Jwts.parserBuilder().setSigningKey(key()).build().parseClaimsJws(authToken);
			return true;
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token: {}", ex.getMessage());
		} catch (ExpiredJwtException ex) {
			logger.error("JWT token is expired: {}", ex.getMessage());
		} catch (UnsupportedJwtException ex) {
			logger.error("JWT token is unsupported: {}", ex.getMessage());
		} catch (IllegalArgumentException ex) {
			logger.error("Invalid argument: {}", ex.getMessage());
		}
		return false;
	}
}
