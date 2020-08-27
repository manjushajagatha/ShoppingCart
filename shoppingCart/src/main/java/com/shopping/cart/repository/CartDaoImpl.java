package com.shopping.cart.repository;

import com.shopping.cart.model.Cart;
import com.shopping.cart.model.Product;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;
/*
 * Cart Repository to implement all the db operations
 *
 */
public interface CartDaoImpl extends CrudRepository<Cart, Integer> {

	@Modifying
	@Transactional
	@Query(value = "UPDATE Cart SET itemQty=:itemQty WHERE cartId =:cartId")
	void updateItemQtyById(int cartId, int itemQty);

	@Modifying
	@Transactional
	@Query(value = "UPDATE Cart SET cartItems=:cartItems WHERE cartId =:cartId")
	void updateCart(int cartId, String cartItems);

	@Query(nativeQuery = true, value = "select * from CART where USER_NME=:userrr")
	List<Cart> findAll(String userrr);

	@Query(nativeQuery = true, value = "select ITEM_QTY from cart where user_Nme=:usernme AND cart_Items=:cartItem")
	int findItemAlreadyAvailable(String usernme, String cartItem);

	
	@Modifying
	@Transactional
	@Query(value = "UPDATE Cart SET itemQty= :itemAlreadyAvailable_size  where userNme=:usernm AND cartItems=:cartItemm")
	void updateCart(String usernm, String cartItemm,int itemAlreadyAvailable_size);
	
	@Modifying
	@Transactional
	@Query(value = "DELETE FROM Cart WHERE USER_NME=:usernm")
	void deleteCart(String usernm);

}