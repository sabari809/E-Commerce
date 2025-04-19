package com.javaproject.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.ecommerce.dto.Customerdto;
import com.javaproject.ecommerce.dto.Merchantdto;
import com.javaproject.ecommerce.service.CustomerService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	
	@GetMapping("/register")
	public String loadRegister(Customerdto customerdto,Model model) {
		return customerService.register(customerdto,model);
	}
	
	@PostMapping("/register")
	public String register(@Valid Customerdto customerdto,BindingResult result){
		return customerService.register(customerdto, result);
	}
}
