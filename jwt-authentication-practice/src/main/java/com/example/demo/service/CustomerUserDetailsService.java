package com.example.demo.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class CustomerUserDetailsService implements UserDetailsService {
	private final UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	    return userRepo.findByUsername(username)
	            .map(user -> org.springframework.security.core.userdetails.User.builder()
	                    .username(user.getUsername())
	                    .password(user.getPassword())
	                    .roles(user.getRoles().toArray(new String[0])) // expects plain names
	                    .build())
	            .orElseThrow(() -> new UsernameNotFoundException("user not found"));
	}


	

}
