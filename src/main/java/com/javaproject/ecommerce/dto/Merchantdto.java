package com.javaproject.ecommerce.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class Merchantdto {

	@Size(min = 5,max = 15,message = "* Name should be have 5~15 characters")
	private String name;
	
	@Email
	@NotEmpty
	private String email;
	
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "* password should have one uppercase, and lowecase and special characters")
	private String password;

	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "* password should have one uppercase, and lowecase and special characters")
	private String Confirmpassword;

	@NotNull
	private LocalDateTime registeredat;
	
	@AssertTrue(message = "Please accpet the terms and contidions")
	private String terms;
}
