package com.online.shopping.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.shopping.entity.User;
import com.online.shopping.repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	public List<List<String>> viewAllUsers(){
		List<User> allUsers = userRepository.findAll();
	}
	
}
