package com.shopping.cart.controller;

import java.util.List;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.shopping.cart.exception.ProductNotFoundException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Product;
import com.shopping.cart.service.CartService;
import com.shopping.cart.service.ProductService;

/*
 * Services on Cart
 */

@RestController
public class CartController {

	@Autowired
	CartService cartService;
	
	@Autowired
	ProductService productservice;
    
	//Get all the Cart Items for a user
	@GetMapping(value = "{user}/cart")
	public @ResponseBody ResponseEntity<List<Cart>> getCartData(@PathVariable("user") final String user)
			throws ProductNotFoundException {
		System.out.println("I am in get all Cart Items");
		List<Cart> cart = cartService.showCart(user);
		return new ResponseEntity<List<Cart>>(cart, HttpStatus.OK);
	}

	//UpdateCart - if item is already available in the cart update Qty or else add item to the Cart
	@PostMapping(value = "{user}/cart/{productCode}")
	public String addToCart(@PathVariable("user") final String user,
			@PathVariable("productCode") final String productCode) throws ProductNotFoundException, JSONException {

		int itemAlreadyAvailable_size = cartService.checkItemInCart(user, productCode);
		if (itemAlreadyAvailable_size > 0) {
			
			itemAlreadyAvailable_size = itemAlreadyAvailable_size + 1;
			System.out.println("Size 2 - " + itemAlreadyAvailable_size);
			cartService.updateCart(user, productCode, itemAlreadyAvailable_size);
		}else {
			  Product product =  productservice.getProduct(productCode);
			  System.out.println("Size of Product received - "+product.getProductCode());
			  Cart c = new Cart();
			  
			  c.setCartId(product.getId());
			  c.setCartItems(product.getProductCode());
			  c.setItemQty(1);
			  c.setUserNme(user);
			  
			  cartService.addItemToCart(c);
		}
		
		return "Item Added to the cart -" + productCode;

	}	

	//Delete cart for the given user
	@DeleteMapping(value = "{user}/cart")
	public String deleteCart(@PathVariable("user") final String user) {
		try {
			cartService.deleteCart(user);
			return "Cart deleted ";
		} catch (Exception e) {
			throw new ProductNotFoundException("No Cart Items not found ");
		}
	}

}
