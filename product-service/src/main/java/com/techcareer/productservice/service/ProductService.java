package com.techcareer.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.techcareer.productservice.repository.ProductRepository;
import com.techcareer.productservice.entity.Product;
import com.techcareer.productservice.results.DataResult;
import com.techcareer.productservice.results.SuccessDataResult;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public DataResult<Product> queryById(Long id)
	{
		Product product = 	productRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("ilgili id ye ait kayıt yok"));
		return  new SuccessDataResult<Product>(product);
	}

	public DataResult<List<Product>> tumUrunler() {
		return new SuccessDataResult<List<Product>>( productRepository.findAll());
	}


	public DataResult<Product> kaydet(Product product)
	{
		return new SuccessDataResult<Product>( productRepository.save(product));
	}

}