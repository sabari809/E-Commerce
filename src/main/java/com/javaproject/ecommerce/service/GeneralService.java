package com.javaproject.ecommerce.service;

import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpSession;

public interface GeneralService {

	public String login(String email, String password, HttpSession session);

	public String logout(HttpSession session);

}
