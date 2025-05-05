package com.javaproject.ecommerce.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.dto.Productdto;
import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

public interface MerchantService {

	public String register(userDto userDto, Model model);

	public String register(userDto userDto, BindingResult result,HttpSession session);

	public String submitOtp(int otp, HttpSession session);

	public String loadHome(HttpSession session);

	public String loadAddProduct(Productdto productdto, Model model, HttpSession session);

	public String addProduct(@Valid Productdto productdto, HttpSession session, BindingResult result);

}
