package com.javaproject.ecommerce.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.javaproject.ecommerce.dto.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class Payments {
	
	@Id
 	@GeneratedValue(strategy = GenerationType.IDENTITY)
 	private Long id;
 	@Column(nullable = false)
 	private Double amount;
 	@Column(nullable = false)
 	@Enumerated(EnumType.STRING)
 	private PaymentStatus status;
 	@CreationTimestamp
 	private LocalDateTime createdTime;
 	@Column(nullable = false, unique = true)
 	private String paymentId;
 	@OneToOne
 	Orders orders;
}
