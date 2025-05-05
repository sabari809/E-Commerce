package com.javaproject.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaproject.ecommerce.dto.userDto;
import com.javaproject.ecommerce.service.CustomerService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {


	@Autowired
	CustomerService customerService;

	
	@GetMapping("/register")
	public String loadRegister(userDto userDto,Model model) {
		return customerService.register(userDto,model);
	}
	
	@PostMapping("/register")
	public String register(@Valid userDto userDto,BindingResult result,HttpSession session){
		return customerService.register(userDto, result,session);
	}
	
	
	@GetMapping("/otp")
	public String submitotp() {
		return "customer-otp.html";
	}
	
	@PostMapping("/otp")
	public String loadOtp(@RequestParam("otp") int otp,HttpSession session) {
		return customerService.submitOtp(otp,session);
	}
	
	@GetMapping("/home")
	public String loadHome(HttpSession session) {
		return customerService.loadHome(session);
	}
}
