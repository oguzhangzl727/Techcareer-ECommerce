package com.techcareer.shoppingcartservice.entity;

import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Product {

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Long getPrice() {
		return price;
	}


	public void setPrice(Long price) {
		this.price = price;
	}

	@Id
	private Long id;
	private String name;
	private int quantity;
	private String pictureurl;
	public String getPictureurl() {
		return pictureurl;
	}


	public void setPictureurl(String pictureurl) {
		this.pictureurl = pictureurl;
	}

	private Long price;

	@ManyToMany(mappedBy = "products")
	private Set<ShoppingCart> shoppingCarts;







}