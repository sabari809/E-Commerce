package com.javaproject.ecommerce.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import com.javaproject.ecommerce.dto.OrderStatus;
import com.javaproject.ecommerce.dto.PaymentStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Orders {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Double totalAmount;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private PaymentStatus paymentsStatus;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)	
	private OrderStatus orderStatus;
	
	@CreationTimestamp
	private LocalDateTime creationTime;
	
	
}
