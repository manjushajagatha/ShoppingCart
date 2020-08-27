package com.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;
/*
 * Cart Entity Class for all the Items in the Class
 *
 */
@Entity
public class Cart {

	@Id
	@Column(name="CART_ID")
	private int cartId;
	
	@Column(name="CART_ITEMS")
	private String cartItems;
	
	@Column(name="USER_NME")
	private String userNme;
	
	@Column(name="ITEM_QTY")
	private int itemQty;
	
	
	
	

	public int getCartId() {
		return cartId;
	}

	public void setCartId(int cartId) {
		this.cartId = cartId;
	}

	public String getCartItems() {
		return cartItems;
	}

	public void setCartItems(String cartItems) {
		this.cartItems = cartItems;
	}


	public String getUserNme() {
		return userNme;
	}

	public void setUserNme(String userNme) {
		this.userNme = userNme;
	}

	public int getItemQty() {
		return itemQty;
	}

	public void setItemQty(int itemQty) {
		this.itemQty = itemQty;
	}
	

	
}
