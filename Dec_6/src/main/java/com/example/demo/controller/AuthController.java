package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.LoginRequest;
import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.JwtService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final PasswordEncoder passwordEncoder;
	private final JwtService jwtService;
	private final AuthenticationManager authManager;
	private final UserRepository userRepo;

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginRequest request) {
	    Authentication auth = authManager.authenticate(
	        new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
	    );
	    UserDetails user = (UserDetails) auth.getPrincipal();
	    List<String> roles = user.getAuthorities().stream().map(a -> a.getAuthority()).toList();
	    String token = jwtService.generateToken(user.getUsername(), roles);
	    return ResponseEntity.ok(token);
	}
	@PostMapping("/signup")
	public ResponseEntity<String> signup(@RequestBody User requestUser) {
		if (userRepo.findByUsername(requestUser.getUsername()).isPresent()) {
			return ResponseEntity.badRequest().body("User exists");
		}
		requestUser.setPassword(passwordEncoder.encode(requestUser.getPassword()));
		if (requestUser.getRoles() == null || requestUser.getRoles().isEmpty()) {
			requestUser.setRoles(List.of("USER"));
		}
		User savedUser = userRepo.save(requestUser);
		String token = jwtService.generateToken(savedUser.getUsername(), savedUser.getRoles());
		return ResponseEntity.ok(token);
	}
}
