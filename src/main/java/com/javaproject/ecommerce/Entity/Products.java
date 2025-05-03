package com.javaproject.ecommerce.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.javaproject.ecommerce.dto.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Products {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	private String description;
	
	@Column(nullable = false)
	private Double price;
	
	@Column(nullable = false)
	private String imageUrl;
	
	@Column(nullable = false)
	private Status status;
	
	
	@UpdateTimestamp
	private LocalDateTime createdTime;
	
}
