package com.techcareer.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcareer.productservice.entity.Product;
import com.techcareer.productservice.results.DataResult;
import com.techcareer.productservice.service.ProductService;

import jakarta.ws.rs.core.MediaType;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value = "product")
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public DataResult<List<Product>> getAll(){
		return productService.tumUrunler();
	}


	@PostMapping(consumes = MediaType.APPLICATION_JSON)
	public DataResult<Product> save(@RequestBody Product product){
		return productService.kaydet(product);
	}

	@GetMapping("{id}")
	public DataResult<Product> queryById(@PathVariable("id") Long id){
		return productService.queryById(id);
	}
}
