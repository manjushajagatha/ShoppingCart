package com.shopping.cart.repository;

import com.shopping.cart.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
/*
 * Product Repository to implement all the db operations
 *
 */
public interface ProductDaoImpl extends CrudRepository<Product, Integer>  {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Product SET productName=:productName WHERE id =:productId")
	void updateProductNameById(int productId, String productName);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Product SET productPrice=:productPrice WHERE id =:productId")
	void updateProductPriceById(int productId, double productPrice);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Product SET productCode=:productCode,productName=:productName,productPrice=:productPrice WHERE id =:productId")
	void updateProduct(int productId, String productCode, String productName, double productPrice);

	@Transactional
	@Query(nativeQuery = true, value = "select * from Product")
	List<Product> findAll(String user);
	

	@Transactional
	@Query(nativeQuery = true, value = "select USER_ROLE from USER_TABLE where USER_NME=:user")
	String getuserRole(String user);

	@Transactional
	@Query(nativeQuery = true, value = "select * from PRODUCT where PRODUCT_CODE=:productCode")
	Product getProduct(String productCode);
	
}