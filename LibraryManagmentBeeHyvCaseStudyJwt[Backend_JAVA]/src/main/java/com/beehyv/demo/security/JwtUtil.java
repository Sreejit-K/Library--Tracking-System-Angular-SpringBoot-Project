package com.beehyv.demo.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.beehyv.demo.model.User;


@Service
public class JwtUtil {
	
	public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 24 * 60 * 60 * 1000;

	public static final String SIGNING_KEY = "sreejith";
	
	public String extrarctUserName(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}
	
	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private Claims extractAllClaims(String token) {
		return Jwts.parser().setSigningKey(SIGNING_KEY).parseClaimsJws(token).getBody();
	}

	private Boolean isTokenExpired(String token) {
		final Date expiration = extractExpiration(token);
		return expiration.before(new Date());
	}
	
	public String generateToken(User user) {
		Map<String,Object> claims = new HashMap<>();
		
		return createToken(claims, user.getUsername());
	}
	
	private String createToken( Map<String, Object> claims , String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject)
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS))
				.signWith(SignatureAlgorithm.HS256, SIGNING_KEY).compact();
	}
	
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extrarctUserName(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	
	
	
	
	
	

}
