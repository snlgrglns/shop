package com.example.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.shop.model.Role;
import com.example.shop.model.UserAuthenticationRequestModel;
import com.example.shop.model.UserRegisterRequestModel;
import com.example.shop.repository.UserRepository;
import com.example.shop.security.AuthenticationResponse;
import com.example.shop.security.JWTService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthneticationService {
	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JWTService jwtService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
//	@Autowired
//	public AuthneticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JWTService jwtService,
//			AuthenticationManager authenticationManager) {
//		super();
//		this.userRepository = userRepository;
//		this.passwordEncoder = passwordEncoder;
//		this.jwtService = jwtService;
//		this.authenticationManager = authenticationManager;
//	}

	
	public AuthenticationResponse register(UserRegisterRequestModel request) {
//		String ppp1 = request.getPassword();
//		String ppp = passwordEncoder.encode(request.getPassword());
		var user = com.example.shop.model.User.builder()				
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(passwordEncoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();
		userRepository.save(user);
		var token = jwtService.generateToken(user);
		return 	AuthenticationResponse.builder().token(token).build();
	}
	
	public AuthenticationResponse authenticate(UserAuthenticationRequestModel request) {
		Authentication authentication;
		try {
		authentication = this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
		System.out.println(authentication.isAuthenticated());
		}catch(Exception ex) {
			System.out.println(ex);
		}
		var user = userRepository.findByEmail(request.getEmail())
				.orElseThrow();
		var token = jwtService.generateToken(user);
		return 	AuthenticationResponse.builder().token(token).build();
	}

}
 