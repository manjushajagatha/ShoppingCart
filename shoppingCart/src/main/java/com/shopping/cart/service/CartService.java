package com.shopping.cart.service;

import java.util.List;

import org.json.JSONException;

import com.shopping.cart.model.Cart;

public interface CartService {

	public Cart getCartById(int id);
	
	public void addItemToCart(String user, int id, int itemQty) throws JSONException;
	
	public List<Cart> showCart(String user);
	
	public void deleteCart(String user);
	
	public int checkItemInCart(String user, String productCode);
	
	public void updateCart(String user, String productCode, int itemAlreadyAvailable_size);
	
	public void addItemToCart(Cart c);
	
}
