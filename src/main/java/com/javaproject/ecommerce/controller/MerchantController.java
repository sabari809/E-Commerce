package com.javaproject.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaproject.ecommerce.dto.Merchantdto;
import com.javaproject.ecommerce.service.MerchantService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/merchant")
public class MerchantController {
	
	@Autowired
	MerchantService merchantService;

	@GetMapping("/register")
	public String loadRegister(Merchantdto merchantdto,Model model) {
		return merchantService.register(merchantdto,model);
	}
	
	@PostMapping("/register")
	public String register(@Valid Merchantdto merchantdto,BindingResult result){
		return merchantService.register(merchantdto, result);
	}
}
