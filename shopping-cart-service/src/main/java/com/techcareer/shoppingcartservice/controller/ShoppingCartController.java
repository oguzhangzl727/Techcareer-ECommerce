package com.techcareer.shoppingcartservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techcareer.shoppingcartservice.entity.Product;
import com.techcareer.shoppingcartservice.entity.ShoppingCart;
import com.techcareer.shoppingcartservice.entity.User;
import com.techcareer.shoppingcartservice.results.DataResult;
import com.techcareer.shoppingcartservice.service.ShoppingCartService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("shopping-cart")
public class ShoppingCartController {

	@Autowired
	ShoppingCartService shoppingCartService;

	@PostMapping("/createcart")
	public DataResult<ShoppingCart> sepetiOlustur(@RequestBody User user){
		return shoppingCartService.createCart(user.getUser_id());
	}


	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addproductscart/{id}/{arttir}")
	public DataResult<ShoppingCart> sepeteUrunleriEkle(@PathVariable("id") Long userId,@RequestBody List<Product> products,@PathVariable("arttir") int artis){
		return shoppingCartService.addProducts(userId,products,artis);
	}


	@GetMapping("/cartprice/{id}")
	public  DataResult<Map<String,String>> toplamFiyat(@PathVariable("id") Long userId) {

		return shoppingCartService.getShoppingCartPrice(userId);
	}

	@GetMapping("/getcartitems/{id}")
	public DataResult<List<Product>> sepettekiUrunler(@PathVariable("id") Long userId) {

		return shoppingCartService.getProductsByUserId(userId);
	}

	@PostMapping("/updatecart/{id}")
	public DataResult<ShoppingCart> sepetiGuncelle(@PathVariable("id") Long shoppingCartId,@RequestBody  List<Product> newProducts) {

		return shoppingCartService.updateCartProducts(shoppingCartId, newProducts);
	}

	@PostMapping("/clearcart/{id}")
	public DataResult<ShoppingCart> sepetiSifirla(@PathVariable("id") Long userId) {

		return shoppingCartService.clearCart(userId);
	}

}
