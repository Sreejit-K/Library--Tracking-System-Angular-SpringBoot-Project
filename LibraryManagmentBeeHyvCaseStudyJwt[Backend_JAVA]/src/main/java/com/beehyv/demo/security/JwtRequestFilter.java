package com.beehyv.demo.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import com.beehyv.demo.service.UserDetailsServiceImp;


@Service
public class JwtRequestFilter extends OncePerRequestFilter {
	
	@Autowired
	private UserDetailsServiceImp userDetailsService;
	
	@Autowired
	private JwtUtil jwtUtil ;

	
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		final String authorizationHeader = request.getHeader(HEADER_STRING);
		
		String username = null;
		String jwt = null;
		
		if (authorizationHeader!= null && authorizationHeader.startsWith(TOKEN_PREFIX)) {
			jwt = authorizationHeader.substring(7);
			username = jwtUtil.extrarctUserName(jwt);
		}
		
		if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			
			if (jwtUtil.validateToken(jwt, userDetails)) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}
			
		chain.doFilter(request, response);
	}
	
}
