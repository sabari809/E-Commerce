package com.javaproject.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.ecommerce.Entity.AdminEntity;

public interface AdminRepo extends JpaRepository<AdminEntity, Long>{
		boolean existsByEmail(String email);
		
		AdminEntity findByEmail(String email);
}
