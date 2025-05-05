package com.javaproject.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.Entity.CustomerEntity;
import com.javaproject.ecommerce.Helper.AES;
import com.javaproject.ecommerce.Helper.EmailSender;
import com.javaproject.ecommerce.Repository.AdminRepo;
import com.javaproject.ecommerce.Repository.CustomerRepo;
import com.javaproject.ecommerce.Repository.MerchantRepo;
import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;

@Service
public class CustomerServiceImpl implements CustomerService {
	
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
		model.addAttribute(userDto);
		return "customer-register.html";
	}
	
	@Override
	public String register(userDto userDto,BindingResult result,HttpSession session) {
		if (!userDto.getPassword().equals(userDto.getConfirmPassword()))result.rejectValue("confirmPassword",  "error.confirmPassword","* Password and confirm password not matching"); 
		if(adminRepo.existsByEmail(userDto.getEmail()) || customerRepo.existsByEmail(userDto.getEmail() )|| merchantRepo.existsByEmail(userDto.getEmail()))result.rejectValue("email", "error.email", "* Email Already Exists");
		if(result.hasErrors()) {
			return "customer-register.html";
		}
		
		int otp = new Random().nextInt(100000, 1000000);
 		emailSender.sendEmail(userDto, otp);
 
 		session.setAttribute("otp", otp);
 		session.setAttribute("userDto", userDto);
 		session.setAttribute("pass", "Otp Sent Success");
 
 		return "redirect:/customer/otp";
 	}
 
	@Override
 	public String submitOtp(int otp, HttpSession session) {
 		int generateOtp = (int) session.getAttribute("otp");
		
 		if (generateOtp == otp) {
			userDto Dto = (userDto) session.getAttribute("userDto"); 
			
			CustomerEntity customerEntity = new CustomerEntity();
			customerEntity.setEmail(Dto.getEmail());
			customerEntity.setName(Dto.getName());
			customerEntity.setPassword(AES.encrypt(Dto.getPassword()));
			customerRepo.save(customerEntity);
			session.setAttribute("pass", "Account Created Successfully");
			session.removeAttribute("otp");
			session.removeAttribute("userDto");
			return "redirect:/customer/home";
 		}else {
 			session.setAttribute("Fail", "Otp Missmatch");
 			return "redirect:/customer/otp";
		}
	}
	
	@Override
	public String loadHome(HttpSession session) {
		CustomerEntity customerEntity = (CustomerEntity) session.getAttribute("customer");
		if (customerEntity!=null) {
			return "customer-home.html";
		}else {
			session.setAttribute("fail", "Invalid Session, First Login to Access");
			return "redirect:/login";
		}
	}
	
}
