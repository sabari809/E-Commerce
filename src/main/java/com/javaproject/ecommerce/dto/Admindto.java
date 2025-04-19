package com.javaproject.ecommerce.dto;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Admindto {

	@Size(min = 5,max = 15,message = "* Name should be within 5 ~ 15")
	private String name;

	@NotEmpty
	@Email(message = "* Enter the Correct Email")
	private String email;
	
	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "* password should contains atleast 8 character, one Uppercase, one lowercase one number and one special character")
	private String password;
	

	@Pattern(regexp = "^.*(?=.{8,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",message = "* password should contains atleast 8 character, one Uppercase, one lowercase one number and one special character")
	private String confirmPassword;
	
	@AssertTrue(message = "* Please accept and proceed the terms and conditions")
	private boolean terms;
}
