package com.shopping.cart.service;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.shopping.cart.exception.ProductNotFoundException;
import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Product;
import com.shopping.cart.repository.CartDaoImpl;

@Component
public class CartServiceImpl implements CartService {

	@Autowired
	CartDaoImpl cartDaoImpl;

	@Autowired
	ProductServiceImpl productServiceImpl;

	public Cart getCartById(@NonNull int id) throws ProductNotFoundException {
		Cart cart = cartDaoImpl.findById(id).orElseThrow(() -> new ProductNotFoundException("Cart not found"));
		System.out.println("print the cart....." + cart);
		if (cart != null)
			return cart;
		else
			return null;
	}

	public void addItemToCart(String user, @NonNull int id, @NonNull int itemQty) throws JSONException {

		Product product = productServiceImpl.getProductById(user, id);
		int cartId = 1;
		JSONObject itemJson = new JSONObject();
		itemJson.put("itemId", product.getId());
		itemJson.put("itemName", product.getProductName());
		itemJson.put("itemQty", itemQty);
		System.out.println("inside add cart " + product.getProductName());
		System.out.println("inside add cart... " + itemJson.getString("itemName"));
		Cart cart;
		JSONArray itemsArray;
		System.out.println("before cart " + itemJson.getString("itemName"));
		boolean cartExists = cartDaoImpl.existsById(1);

		System.out.println("cartExists value " + cartExists);

		System.out.println("after add cart " + itemJson.getString("itemName"));
		if (cartExists) {
			cart = getCartById(1);
			String items = cart.getCartItems();
			itemsArray = new JSONArray(items);
			itemsArray.put(itemJson);
			cartDaoImpl.updateCart(cartId, itemsArray.toString());
		} else {
			cart = new Cart();
			itemsArray = new JSONArray();
			itemsArray.put(itemJson);
			cart.setCartId(1);
			cart.setCartItems(itemsArray.toString());
			cartDaoImpl.save(cart);
		}

	}


	public List<Cart> showCart(String user) throws ProductNotFoundException {

		List<Cart> cart = cartDaoImpl.findAll(user);
		if (((List<Cart>) cart).isEmpty() || cart == null)
			throw new ProductNotFoundException();
		else
			return (List<Cart>) cart;
	}

	public int checkItemInCart(String user, String productCode) {
	  int i = 0;
	  try{
		  int  itemAlreadyAvailable_size =  cartDaoImpl.findItemAlreadyAvailable(user,productCode);
		  return itemAlreadyAvailable_size;
	  }catch(Exception e) {
		  return i;
	  }
	}

	public void updateCart(String user, String productCode, int itemAlreadyAvailable_size) {
		 cartDaoImpl.updateCart(user,productCode,itemAlreadyAvailable_size);		
	}

	public void addItemToCart(Cart c) {
		cartDaoImpl.save(c);		
	}
	
	public void deleteCart(String user) {
		cartDaoImpl.deleteCart(user);
	}

}
