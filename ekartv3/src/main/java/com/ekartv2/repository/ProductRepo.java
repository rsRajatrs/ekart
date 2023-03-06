package com.ekartv2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ekartv2.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	List<Product> findBySellerId(Integer sellerId);
	
	List<Product> findByCategory(String category);
	
	List<Product> findByProductNameContaining(String keyword);
	
	List<Product> findByProductName(String productName);

}
