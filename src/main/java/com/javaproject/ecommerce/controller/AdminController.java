package com.javaproject.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.ecommerce.dto.Admindto;
import com.javaproject.ecommerce.service.AdminService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	AdminService adminService;
		
	@GetMapping("/register")
	public String loadRegister(Admindto admindto,Model model) {
		return adminService.register(admindto,model);
	}
	
	@PostMapping("/register")
	public String Register(@Valid Admindto admindto, BindingResult result) {
		return adminService.register(admindto, result);
	}
	
}
