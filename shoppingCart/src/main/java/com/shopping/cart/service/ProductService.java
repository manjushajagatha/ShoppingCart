package com.shopping.cart.service;

import java.util.List;

import com.shopping.cart.model.Product;

public interface ProductService {

	public List<Product> findAll(String user);
	
	public Product getProductById(String user, int id);
	
	public String getuserRole(String user);
	
	public Product addProduct(Product product);
	
	public void updateProduct(int id, Product product);
	
	public void deleteProduct(int id);

	public Product getProduct(String productCode);
	
}
