package com.sai.jwt.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sai.jwt.model.JwtRequest;
import com.sai.jwt.model.JwtResponse;
import com.sai.jwt.model.RegistrationRequest;
import com.sai.jwt.model.Role;
import com.sai.jwt.model.User;
import com.sai.jwt.repository.UserRepository;
import com.sai.jwt.security.JwtHelper;

@Service
public class AuthenticationService {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Autowired
	private JwtHelper jwtHelper;
	
	@Autowired
	private AuthenticationManager authenticationManager;

	public JwtResponse authenticate(JwtRequest request) {
		authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
				);
		var user = userRepository.findByEmail(request.getEmail()).orElseThrow(()-> new UsernameNotFoundException("user name not found"));
		var jwtToken = jwtHelper.generateToken(user);
		return JwtResponse.builder().jwttoken(jwtToken).build();
	}

	public JwtResponse register(RegistrationRequest request) {
		var user = User.builder()
				.firstName(request.getFirstName())
				.lastName(request.getLastName())
				.email(request.getEmail())
				.password(encoder.encode(request.getPassword()))
				.role(Role.USER)
				.build();			
		userRepository.save(user);
		var jwtToken = jwtHelper.generateToken(user);
		return JwtResponse.builder().jwttoken(jwtToken).build();
	}
	
	
}
