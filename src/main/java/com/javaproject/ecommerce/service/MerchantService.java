package com.javaproject.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.dto.Merchantdto;

import jakarta.validation.Valid;

@Service
public class MerchantService {

	public String register(Merchantdto merchantdto, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	public String register(@Valid Merchantdto merchantdto, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
