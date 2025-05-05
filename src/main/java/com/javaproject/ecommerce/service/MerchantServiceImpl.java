package com.javaproject.ecommerce.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import com.javaproject.ecommerce.Entity.MerchantEntity;
import com.javaproject.ecommerce.Entity.Products;
import com.javaproject.ecommerce.Helper.AES;
import com.javaproject.ecommerce.Helper.CloudinaryHelper;
import com.javaproject.ecommerce.Helper.EmailSender;
import com.javaproject.ecommerce.Repository.AdminRepo;
import com.javaproject.ecommerce.Repository.CustomerRepo;
import com.javaproject.ecommerce.Repository.MerchantRepo;
import com.javaproject.ecommerce.Repository.ProductRepo;
import com.javaproject.ecommerce.controller.AdminController;
import com.javaproject.ecommerce.dto.Productdto;
import com.javaproject.ecommerce.dto.Status;
import com.javaproject.ecommerce.dto.userDto;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Service
public class MerchantServiceImpl implements MerchantService{

	@Autowired
	AdminRepo adminRepo;
	
	@Autowired
	CustomerRepo customerRepo;
	
	@Autowired
	MerchantRepo merchantRepo;
	
	@Autowired
	ProductRepo productRepo;
	
	@Autowired
	EmailSender emailSender;
	
	@Autowired
	CloudinaryHelper cloudinaryHelper;


  

	
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
	
	@Override
	public String loadHome(HttpSession session) {
		MerchantEntity merchantEntity = (MerchantEntity) session.getAttribute("merchant");
		
		if (merchantEntity!=null) {
			return "merchant-home.html";
		}else {
			return "redirect:login";
		}
	
	}
	
	@Override
	public String loadAddProduct(Productdto productdto, Model model, HttpSession session) {
		
		MerchantEntity merchantEntity = (MerchantEntity) session.getAttribute("merchant");
		
		if (merchantEntity!=null) {
			model.addAttribute("productdto",productdto);
			return "add-product.html";
		}else {
			session.setAttribute("fail", "Invalid Session First Login to Access");
			return "redirect:/login";
		}
		
		
	}
	
	@Override
	public String addProduct(@Valid Productdto productdto, HttpSession session, BindingResult result) {
		MerchantEntity merchantEntity = (MerchantEntity) session.getAttribute("merchant");
		
		if(merchantEntity!=null) {
			if(productdto.getImage().isEmpty())result.rejectValue("image", "error.image","* Select One Image");
		if (result.hasErrors()) {
			return "add-product.html";
		}else {
			Products products = new Products();
			products.setName(productdto.getName());
			products.setCategory(productdto.getCategory());
			products.setDescription(productdto.getDescription());
			products.setStock(productdto.getStock());
			products.setPrice(productdto.getPrice());
			products.setImageUrl(cloudinaryHelper.saveImage(productdto.getImage()));
			products.setMerchant(merchantEntity);
			products.setStatus(Status.PENDING);
			productRepo.save(products);
			session.setAttribute("pass", "Product Added Success");
			return "redirect:/merchant/home";
		}}else {
			session.setAttribute("fail", "Invalid Session, First Login to Access");
			return "redirect:/login";
		}}
			
	
	
	
}
