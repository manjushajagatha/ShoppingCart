package com.shopping.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

/*
 * Product Entity Class
 *
 */

@SuppressWarnings("unused")
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID")
	private int id;

	@Column(name = "PRODUCT_CODE")
	private String productCode;

	@Column(name = "PRODUCT_NAME")
	private String productName;

	@Column(name = "PRODUCT_PRICE")
	private double productPrice;

	public Product() {
		
	}
	public Product(int id, String productCode, String productName,double productPrice)
	{
		this.id =id;
		this.productCode= productCode;
		this.productName=productName;
		this.productPrice=productPrice;
	}
	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

}
