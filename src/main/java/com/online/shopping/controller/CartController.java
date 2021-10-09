package com.online.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.online.shopping.service.CartService;
import com.online.shopping.service.CustomerService;

@RestController
public class CartController {
	@Autowired
	private CustomerService customerService;
	@Autowired
	private CartService cartService;
	
}
