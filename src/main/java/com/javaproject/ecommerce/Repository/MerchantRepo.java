package com.javaproject.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.ecommerce.Entity.MerchantEntity;

public interface MerchantRepo extends JpaRepository<MerchantEntity, Long> {
	
	boolean existsByEmail(String email);
	
	MerchantEntity findByEmail(String email);
}
