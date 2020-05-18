package org.dev.fhhf.hulkstore.service;

import static org.junit.jupiter.api.Assertions.*;

import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.repository.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

	@Mock
	private ProductRepo productRepo;
	@InjectMocks
	private ProductServiceImpl productService;
	
	@Test
	@DisplayName("Obteniendo la lista vacía de productos")
	void testGetEmptyListOfProducts() {
		
		Product product1 = new Product(1, "Suit", "IronMan", "Marvel", 100);
		Product product2 = new Product(2, "Hammer", "Thor", "Marvel", 50);
		Product product3 = new Product(3, "Cards", "Joker", "DC", 70);
		
		when(productRepo.findAll()).thenReturn(Arrays.asList(product1, product2, product3));
		
		final int expectedUnits = 0;
		
		final List<Product> actual = productService.getEmptyListOfProducts();
		
		assertAll(() -> {
					for (Product p : actual) {
						assertEquals(expectedUnits, p.getUnits(), "La lista vacía debe tener 0 unidades por producto");
					}
				});
	}

}
