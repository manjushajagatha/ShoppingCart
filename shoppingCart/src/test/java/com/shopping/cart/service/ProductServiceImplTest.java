package com.shopping.cart.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import com.shopping.cart.model.Product;
import com.shopping.cart.repository.ProductDaoImpl;

@ExtendWith(MockitoExtension.class)
public class ProductServiceImplTest {

	@InjectMocks
	private ProductServiceImpl serviceImpl;

	@Mock
	private ProductDaoImpl productDaoImpl;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAll() {
		String user = "sven";
		when(productDaoImpl.findAll(user)).thenReturn(
				Arrays.asList(new Product(1, "BAKE", "Bread", 10.00), new Product(2, "DAIRY", "Cheese", 20.00)));
		List<Product> products = productDaoImpl.findAll(user);
		assertEquals(1, products.get(0).getId());
	}

	@Test
	public void addProduct() {
		Product p1 = new Product(1, "DAIRY", "Butter", 12.35);
		 serviceImpl.addProduct(p1);
		 verify(productDaoImpl, times(1)).save(p1);
		
	}
}
