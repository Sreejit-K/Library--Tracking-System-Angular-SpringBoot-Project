package com.beehyv.demo.service;


import java.util.Arrays;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.beehyv.demo.model.User;
import com.beehyv.demo.repository.UserRepository;

//<----------------------------------------------------------------------------------------------------------------------------->	
@Service
public class UserDetailsServiceImp implements UserDetailsService {
	@Autowired
	private UserRepository userRepository; 
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByName(username);
		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password.");
		}
		return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
				Arrays.asList(new SimpleGrantedAuthority("USER")));
	}

}
//<----------------------------------------------------------------------------------------------------------------------------->	