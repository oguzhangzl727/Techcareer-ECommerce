package com.techcareer.productservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.techcareer.productservice.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	Optional<Product> findById(Long Id);
}
