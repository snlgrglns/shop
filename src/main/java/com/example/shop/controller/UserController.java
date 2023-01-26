package com.example.shop.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.shop.model.UserAuthenticationRequestModel;
import com.example.shop.model.UserRegisterRequestModel;
import com.example.shop.security.AuthenticationResponse;
import com.example.shop.service.AuthneticationService;

@CrossOrigin(origins="http://localhost:3000/")
@RestController
@RequestMapping("/shop/api/auth")
public class UserController {
	
	@Autowired
	private AuthneticationService authneticationService;

	@GetMapping("/register")
	public String getUser() {
		return "afd";
	}
	
	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> createUser(@RequestBody UserRegisterRequestModel request) {
		return ResponseEntity.ok(authneticationService.register(request));
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody UserAuthenticationRequestModel request) {
		return ResponseEntity.ok(authneticationService.authenticate(request));
	}
	
	@GetMapping("/refreshToken")
	public ResponseEntity<AuthenticationResponse> refreshTOken(@RequestBody UserAuthenticationRequestModel request) {
		return ResponseEntity.ok(authneticationService.authenticate(request));
	}
}
