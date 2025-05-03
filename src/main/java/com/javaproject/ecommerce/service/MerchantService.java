package com.javaproject.ecommerce.service;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;

public interface MerchantService {

	public String register(userDto userDto, Model model);

	public String register(userDto userDto, BindingResult result,HttpSession session);

	public String submitOtp(int otp, HttpSession session);

}
