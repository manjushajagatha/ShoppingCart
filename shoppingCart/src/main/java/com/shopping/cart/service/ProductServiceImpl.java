package com.shopping.cart.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import com.shopping.cart.exception.ProductNotFoundException;
import com.shopping.cart.model.Product;
import com.shopping.cart.repository.ProductDaoImpl;

@Component
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDaoImpl productDaoImpl;

	public List<Product> findAll(String user) throws ProductNotFoundException {
		System.out.println("I am in ListOfProducts - 2");
		List<Product> products = productDaoImpl.findAll(user);
		System.out.println("Size of products -"+products.size());
		if (( products).isEmpty() || products == null)
			throw new ProductNotFoundException();
		else
			return  products;
	}

	public Product getProductById(String user, @NonNull int id) throws ProductNotFoundException {
		Product product = productDaoImpl.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));
		if (product != null)
			return product;
		else
			throw new ProductNotFoundException();
	}

	public Product addProduct(Product product) {
		 return productDaoImpl.save(product);
		//System.out.println("Product added Successfully");
	}

	public void updateProduct(@NonNull int id, Product productData) throws ProductNotFoundException {
		System.out.println("I am in Update");
		Product product = productDaoImpl.findById(id)
				.orElseThrow(() -> new ProductNotFoundException("Product not found"));
		System.out.println("product found : "+product);
		String productCode = productData.getProductCode();
		String productName = productData.getProductName();
		double productPrice = productData.getProductPrice();

		productDaoImpl.updateProduct(id, productCode, productName, productPrice);
	}
	

	public void deleteProduct(@NonNull int id) {
		productDaoImpl.deleteById(id);
	}

	public String getuserRole(String user) {

		String userRole= productDaoImpl.getuserRole(user);
		
		return userRole;
	}

	@Override
	public Product getProduct(String productCode) {
		
		Product product = productDaoImpl.getProduct(productCode);

		return product;
	}

}
