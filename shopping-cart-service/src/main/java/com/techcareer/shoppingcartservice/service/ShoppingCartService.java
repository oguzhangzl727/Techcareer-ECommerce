package com.techcareer.shoppingcartservice.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.techcareer.shoppingcartservice.entity.Product;
import com.techcareer.shoppingcartservice.entity.ShoppingCart;
import com.techcareer.shoppingcartservice.repository.ProductRepository;
import com.techcareer.shoppingcartservice.repository.ShoppingCartRepository;
import com.techcareer.shoppingcartservice.results.DataResult;
import com.techcareer.shoppingcartservice.results.ErrorDataResult;
import com.techcareer.shoppingcartservice.results.SuccessDataResult;

@Service
public class ShoppingCartService {

	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	RestTemplate restTemplate;


	public DataResult<ShoppingCart> createCart(Long id){

		ShoppingCart shoppingCart=new ShoppingCart();

		shoppingCart.setUser_id(id);

		return new SuccessDataResult<ShoppingCart>(shoppingCartRepository.save(shoppingCart));
	}

	public DataResult<ShoppingCart> addProducts(Long userId, List<Product> products,int artis) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
				.orElseThrow(() -> new NoSuchElementException("Verilen id ile eşleşen bir sonuç yok"));

		for (Product incomingProduct : products) {
			updateOrAddProduct(shoppingCart, incomingProduct, artis);
		}

		return new SuccessDataResult<ShoppingCart>(shoppingCartRepository.save(shoppingCart));
	}

	private void updateOrAddProduct(ShoppingCart shoppingCart, Product incomingProduct, int artis) {
		boolean productExists = false;

		for (Product existingProduct : shoppingCart.getProducts()) {
			if (existingProduct.getId().equals(incomingProduct.getId())) {
				// Product exists, increment quantity
				existingProduct.setQuantity(existingProduct.getQuantity() + artis);
				productExists = true;
				break;
			}
		}

		if (!productExists) {
			// Product doesn't exist, add it to the shopping cart
			incomingProduct.setQuantity(artis);
			shoppingCart.getProducts().add(incomingProduct);
		}
	}




	public DataResult<Map<String,String>> getShoppingCartPrice(Long userId){
		Map<String,String> response = new HashMap<>();
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç yok"));

		int totalPrice = shoppingCart.getProducts().stream().map(product ->
						restTemplate.getForObject("http://PRODUCT-SERVICE/product/"+product.getId(),HashMap.class))
				.mapToInt(productResponse -> (int) productResponse.get("price"))
				.sum();
		response.put("Toplam Fiyat", Double.toString(totalPrice));
		return new DataResult<Map<String,String>>(true, response) ;

	}

	public DataResult<List<Product>> getProductsByUserId(Long userId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç yok"));
		if (shoppingCart != null) {
			List<Product> products = new ArrayList<>(shoppingCart.getProducts());
			return new SuccessDataResult<List<Product>>(products);
		} else {
			return new ErrorDataResult<List<Product>>("Kullanıcıya ait sepet yok");
		}

	}

	public DataResult<ShoppingCart> updateCartProducts(Long userId, List<Product> newProducts) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç yok"));
		Set<Product> existingProducts = shoppingCart.getProducts();
		existingProducts.clear();
		existingProducts.addAll(newProducts);
		shoppingCart.setProducts(existingProducts);
		return new SuccessDataResult<ShoppingCart>(shoppingCartRepository.save(shoppingCart));
	}


	public DataResult<ShoppingCart> clearCart(Long userId) {
		ShoppingCart shoppingCart = shoppingCartRepository.findByUserId(userId)
				.orElseThrow(() -> new RuntimeException("Verilen id ile eşleşen bir sonuç yok"));
		shoppingCart.getProducts().clear();
		return new SuccessDataResult<ShoppingCart>(shoppingCartRepository.save(shoppingCart));
	}












}
