package com.javaproject.ecommerce.service;


import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.Entity.AdminEntity;
import com.javaproject.ecommerce.Helper.AES;
import com.javaproject.ecommerce.Helper.EmailSender;
import com.javaproject.ecommerce.Repository.AdminRepo;
import com.javaproject.ecommerce.Repository.CustomerRepo;
import com.javaproject.ecommerce.Repository.MerchantRepo;
import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;

@Service
public class AdminServiceImpl implements AdminService {
	
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MerchantRepo merchantRepo;
	
	@Autowired
	EmailSender emailSender;
	
	@Override
	public String register(userDto userDto, Model model) {
		model.addAttribute("userDto",userDto);
		return "admin-register.html";
	}
	
	public String register(userDto userDto,BindingResult result, HttpSession session) {
		if(!userDto.getPassword().equals(userDto.getConfirmPassword()))result.rejectValue("confirmPassword","error.confirmPassword","* Password and confirm password not matching");;
		if (adminRepo.existsByEmail(userDto.getEmail()) || customerRepo.existsByEmail(userDto.getEmail()) || merchantRepo.existsByEmail(userDto.getEmail()))result.rejectValue("email", "error.email", "* Email is already Exists"); 
		if (result.hasErrors()) {
				return "admin-register.html";
			}
		
		int otp = new Random().nextInt(100000, 1000000);
		
		emailSender.sendEmail(userDto, otp);
		
		session.setAttribute("otp", otp);
		session.setAttribute("userDto", userDto);
		session.setAttribute("pass", "Otp Sent Success");

		return "redirect:/admin/otp";
	}	
		@Override
		public String submitOtp(int otp,HttpSession session) {
			int generateOtp = (int) session.getAttribute("otp");
			System.out.println(generateOtp);
	
			if (generateOtp== otp) {
				userDto dto = (userDto) session.getAttribute("userDto");
				AdminEntity admin = new AdminEntity();
				admin.setEmail(dto.getEmail());
				admin.setName(dto.getName());
				admin.setPassword(AES.encrypt(dto.getPassword()));
				adminRepo.save(admin);
				session.setAttribute("Pass", "Account Created Success");
				return "redirect:/admin/home";
			}else {
				session.setAttribute("fail", "Otp Missmatch");
				return "redirect:/admin/otp";
			}
			
			
	}
}
