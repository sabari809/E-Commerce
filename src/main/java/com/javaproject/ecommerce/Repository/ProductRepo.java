package com.javaproject.ecommerce.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaproject.ecommerce.Entity.Products;

public interface ProductRepo extends JpaRepository<Products, Long> {

}
