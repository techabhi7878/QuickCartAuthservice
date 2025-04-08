package com.ecommerce.QuickCarts.AuthService.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.QuickCarts.AuthService.Entity.User;
import com.ecommerce.QuickCarts.AuthService.Repository.UserRepository;

@Service
public class UserService  implements UserDetailsService  {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	
	
	public boolean saveUser(User u) {
		
		String encode = passwordEncoder.encode(u.getPassword());
		u.setPassword(encode);
		
		User savedUser = userRepository.save(u);
		
		return  savedUser.getId()  !=null;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		 User user = userRepository.findByUsername(username)
	                .orElseThrow(() -> new UsernameNotFoundException("User not found!"));

	        return new org.springframework.security.core.userdetails.User(
	                user.getUsername(),
	                user.getPassword(),
	                Collections.emptyList()
	        );
	    }
    }
	
	
	 


	


