package com.shopping.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.cart.exception.ProductNotFoundException;
import com.shopping.cart.model.Product;
import com.shopping.cart.service.ProductService;
/*
 * Services on Products
 *
 */
@RestController
public class ProductController {

	@Autowired
	private ProductService productService;

	
	//Gets all the products for a particular user
	@GetMapping(value = "{user}/products")
	public @ResponseBody ResponseEntity<List<Product>> getAllProducts(@PathVariable("user") final String user)
			throws ProductNotFoundException {
		System.out.println("I am in ListOfProducts - "+user);
		List<Product> products = productService.findAll(user);
		return new ResponseEntity<List<Product>>(products, HttpStatus.OK);
	}

	//Gets product by id based on User 
	@GetMapping(value = "{user}/products/{id}")
	public ResponseEntity<Product> getProductInfo(@PathVariable("user") final String user,
			@PathVariable("id") final int id) {
		try {
			Product product = productService.getProductById(user, id);
			return ResponseEntity.ok().body(product);
		} catch (Exception e) {
			throw new ProductNotFoundException("Product ID not found - " + id);
		}
	}

	//add Products
	@PostMapping("{user}/products")
	public Product addProduct(@PathVariable("user") final String user, @RequestBody Product product) {

		System.out.println("user - "+user);
		String userRole = productService.getuserRole(user);
		if (userRole.equals("manager")) {
			Product p = productService.addProduct(product);
		} else {
			throw new ProductNotFoundException("You do not have Permission to Add Product - " + user);
		}
		return product;
	}

	//update product based on id
	@PutMapping(value = "{user}/products/{id}")
	public Product updateProduct(@PathVariable("user") final String user, @PathVariable("id") final int id,@RequestBody Product product) {
	
		String userRole = productService.getuserRole(user);
		if (userRole.equals("manager")) {
			productService.updateProduct(id, product);
		}else{
			throw new ProductNotFoundException("You do not have Permission to Update Product - " + id);
		}
		return product;

	}

	//Delete a product based on id
	@DeleteMapping(value = "{user}/products/{id}")
	public String deleteProductInfo(@PathVariable("user") final String user, @PathVariable("id") final int id) {
			String userRole = productService.getuserRole(user);
			if (userRole.equals("manager")) {
				productService.deleteProduct(id);
			} else {
				throw new ProductNotFoundException("You do not have Permission to Delete Product - " + id);
			}
			return "Product deleted -" + id;	
		
	}

}
