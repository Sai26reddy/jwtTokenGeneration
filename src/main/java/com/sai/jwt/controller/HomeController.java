package com.sai.jwt.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vi/home")
public class HomeController {
	
	@GetMapping("/getDetails")
	ResponseEntity<String> getDetails(){
		return ResponseEntity.ok("i am in ");
	}
	
}
