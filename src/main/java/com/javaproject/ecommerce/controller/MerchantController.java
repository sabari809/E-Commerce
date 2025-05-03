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
import com.javaproject.ecommerce.service.MerchantService;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	
	@Autowired
	MerchantService merchantService;

	@GetMapping("/register")
	public String loadRegister(userDto userDto,Model model) {
		return merchantService.register(userDto,model);
	}
	
	@PostMapping("/register")
	public String register(@Valid userDto userDto,BindingResult result,HttpSession session){
		return merchantService.register(userDto, result,session);
	}
	
	@GetMapping("/otp")
	public String loadotp() {
		return "merchant-otp.html";
	}
	
	@PostMapping("/otp")
	public String submitOtp(@RequestParam("otp") int otp, HttpSession session) {
		return merchantService.submitOtp(otp,session);
	}
	
	@GetMapping("/home")
 	public String loadHome(HttpSession session) {
		 session.removeAttribute("pass");
		 session.removeAttribute("fail");
 		return "merchant-home.html";
 	}
	
}
