package com.sai.jwt.userservice;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sai.jwt.model.User;

@Service
public class Userservice {
	
	private List<User> store = new ArrayList<>();

	public Userservice() {

	}

	public List<User> getUsers() {
		return this.store;
	}
	
	
}
