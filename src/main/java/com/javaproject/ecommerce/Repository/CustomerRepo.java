package com.javaproject.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.ecommerce.Entity.CustomerEntity;

public interface CustomerRepo extends JpaRepository<CustomerEntity, Long>{
	boolean existsByEmail(String email);
	
	CustomerEntity findByEmail(String email);
}
