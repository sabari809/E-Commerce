package com.javaproject.ecommerce.Entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.javaproject.ecommerce.dto.Status;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	@Enumerated(EnumType.STRING)
	private Status status;
	
	@ManyToOne
    @JoinColumn(name = "merchant_id")
    private MerchantEntity merchant;
	
	@Column(nullable = false)
	private Integer stock;
	
	@UpdateTimestamp
	private LocalDateTime createdTime;

	
}
