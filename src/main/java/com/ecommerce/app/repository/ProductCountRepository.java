package com.ecommerce.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.app.entity.Product;
import com.ecommerce.app.entity.ProductCount;

public interface ProductCountRepository extends JpaRepository<ProductCount, Integer> {
	
	public Optional<ProductCount> findByProduct(Product product);

}
