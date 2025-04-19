package com.javaproject.ecommerce.service;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.dto.Admindto;

import jakarta.validation.Valid;

@Service
public class AdminService {

	public String register(Admindto admindto, Model model) {
		// TODO Auto-generated method stub
		return null;
	}

	public String register(@Valid Admindto admindto, BindingResult result) {
		// TODO Auto-generated method stub
		return null;
	}

}
