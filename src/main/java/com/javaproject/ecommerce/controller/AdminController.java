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
import com.javaproject.ecommerce.service.AdminService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
		
	@GetMapping("/register")
	public String loadRegister(userDto userDto,Model model) {
		return adminService.register(userDto,model);
	}
	
	@PostMapping("/register")
	public String Register(@Valid userDto userDto, BindingResult result, HttpSession session) {
		return adminService.register(userDto, result, session);
	}
	
	@GetMapping("/otp")
	public String loadOtp() {
		return "admin-otp.html";
	}
	
	@PostMapping("/otp")
	public String submitOtp(@RequestParam("otp") int otp,HttpSession session) {
		return adminService.submitOtp(otp,session);
	}
	
	@GetMapping("/home")
	public String loadHome() {
		return "admin-home.html";
	}
	
}
