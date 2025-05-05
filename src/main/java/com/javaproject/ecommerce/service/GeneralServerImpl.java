package com.javaproject.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaproject.ecommerce.Entity.AdminEntity;
import com.javaproject.ecommerce.Entity.CustomerEntity;
import com.javaproject.ecommerce.Entity.MerchantEntity;
import com.javaproject.ecommerce.Helper.AES;
import com.javaproject.ecommerce.Repository.AdminRepo;
import com.javaproject.ecommerce.Repository.CustomerRepo;
import com.javaproject.ecommerce.Repository.MerchantRepo;
import com.javaproject.ecommerce.controller.CustomerController;
import jakarta.servlet.http.HttpSession;

@Service
public class GeneralServerImpl implements GeneralService {
	
	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MerchantRepo merchantRepo;

	@Override
	public String login(String email, String password, HttpSession session) {
		

		session.removeAttribute("admin");
		session.removeAttribute("customer");
		session.removeAttribute("merchant");
		
		
		AdminEntity adminEntity = adminRepo.findByEmail(email);
		
		CustomerEntity customerEntity = customerRepo.findByEmail(email);
		
		MerchantEntity merchantEntity = merchantRepo.findByEmail(email);
		
		if (adminEntity==null && customerEntity == null && merchantEntity == null) {
			session.setAttribute("fail", "Email not exist please create new account");
			return "redirect:/login";
		}
		
		if (adminEntity!=null) {
			if (AES.decrypt(adminEntity.getPassword()).equals(password)) {
				session.setAttribute("admin", adminEntity);
				session.setAttribute("pass", "Login Success");
				return "redirect:/admin/home";
			}else {
				session.setAttribute("fail", "Incorrect Password");
				return "redirect:/login";
			}
		}
		
		if (customerEntity!=null) {
			if (AES.decrypt(customerEntity.getPassword()).equals(password)) {
				session.setAttribute("customer", customerEntity);
				session.setAttribute("pass", "Login Success");
				return "redirect:/customer/home";
			}else {
				session.setAttribute("fail", "Incorrect Password");
				return "redirect:/login";
			}
			
		}
		
		if (merchantEntity!=null) {
			if (AES.decrypt(merchantEntity.getPassword()).equals(password)) {
				session.setAttribute("merchant", merchantEntity);
				session.setAttribute("pass", "Login Success");
				return "redirect:/merchant/home";
			}else {
				session.setAttribute("fail", "Incorrect Password");
				return "redirect:/login";
			}
			
		}
		return "redirect:/login";
	}
	
	@Override
	public String logout(HttpSession session) {
		session.removeAttribute("admin");
		session.removeAttribute("merchant");
		session.removeAttribute("customer");
		return "redirect:/login";
	}
	
}
