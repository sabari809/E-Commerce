package com.javaproject.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.Entity.MerchantEntity;
import com.javaproject.ecommerce.Helper.AES;
import com.javaproject.ecommerce.Helper.EmailSender;
import com.javaproject.ecommerce.Repository.AdminRepo;
import com.javaproject.ecommerce.Repository.CustomerRepo;
import com.javaproject.ecommerce.Repository.MerchantRepo;
import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;

@Service
public class MerchantServiceImpl implements MerchantService{

	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MerchantRepo merchantRepo;
	
	@Autowired
	EmailSender emailSender;
	
	@Override
	public String register(userDto userDto,Model model) {
		model.addAttribute("userDto",userDto);
		return "merchant-register.html";
	}
	
	@Override
	public String register(userDto userDto,BindingResult result,HttpSession session) {
		if (!userDto.getPassword().equals(userDto.getConfirmPassword()))result.rejectValue("confirmPassword", "error.confirmPassword","* Password and confirmpassword not matching");
		if(adminRepo.existsByEmail(userDto.getEmail()) || merchantRepo.existsByEmail(userDto.getEmail()) || customerRepo.existsByEmail(userDto.getEmail()))result.rejectValue("email", "error.email","* Email Already Exists");
		if (result.hasErrors()) {
			return "merchant-register.html";
		}
		int otp = new Random().nextInt(100000,1000000);
		
		emailSender.sendEmail(userDto, otp);
		
		session.setAttribute("otp", otp);
		session.setAttribute("userDto", userDto);
		session.setAttribute("pass", "OTP sent successfully");
		return "redirect:/merchant/otp";
	}
	
	@Override
 	public String submitOtp(int otp, HttpSession session) {
 		int generatedOtp = (int) session.getAttribute("otp");
 		if (generatedOtp == otp) {
 			userDto dto = (userDto) session.getAttribute("userDto");
 			MerchantEntity merchant = new MerchantEntity();
 			merchant.setEmail(dto.getEmail());
 			merchant.setName(dto.getName());
 			merchant.setPassword(AES.encrypt(dto.getPassword()));
 			merchantRepo.save(merchant);
 			session.setAttribute("pass", "Account Created Success");
 			
 			return "redirect:/merchant/home";
 		} else {
 			session.setAttribute("fail", "Otp Missmatch");
 			return "redirect:/merchant/otp";
 		}
 	}
}
