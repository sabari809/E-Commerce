package com.javaproject.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.dto.Customerdto;

import jakarta.validation.Valid;

@Service
public class CustomerService {

	public String register(Customerdto customerdto, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	public String register(@Valid Customerdto customerdto, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
