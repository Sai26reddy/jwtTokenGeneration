package com.sai.jwt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sai.jwt.model.JwtRequest;
import com.sai.jwt.model.JwtResponse;
import com.sai.jwt.model.RegistrationRequest;
import com.sai.jwt.userservice.AuthenticationService;

import lombok.extern.slf4j.Slf4j;



@RestController
@RequestMapping("/api/v1/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	private  AuthenticationService authenticationService;
	
	
	@PostMapping("/authenticate")
	public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request){
		log.info("Reached controller");
		return ResponseEntity.ok(authenticationService.authenticate(request));
	}
	
	@PostMapping("/register")
	public ResponseEntity<JwtResponse> register(@RequestBody RegistrationRequest request){
		return ResponseEntity.ok(authenticationService.register(request));
	}
	

	
	
	
}
