package org.dev.fhhf.hulkstore.service;

import static org.junit.jupiter.api.Assertions.*;

import org.dev.fhhf.hulkstore.exception.NotEnoughStockException;
import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.repository.ProductRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
	
	@Test
	@DisplayName("Actualizando el registro de movimiento de unidades")
	void testUpdateMovedUnits() throws NoSuchMethodException, SecurityException,
								IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		Product initialProduct = new Product(1, "Suit", "IronMan", "Marvel", 0);
		String movedUnits = "";
		Product addedProduct = new Product(1, "Suit", "IronMan", "Marvel", 100);
		
		Method updateMovedUnits = ProductServiceImpl.class.getDeclaredMethod("updateMovedUnits", Product.class, String.class);
		updateMovedUnits.setAccessible(true);
		
		when(productRepo.findById(1)).thenReturn(Optional.of(initialProduct));
		
		final String expected = movedUnits + initialProduct.getId() +
				   				" " + initialProduct.getUnits() +
				   				" " + addedProduct.getUnits() + ",";
		
		final String actualMovedUnits = (String) updateMovedUnits.invoke(productService, addedProduct, movedUnits);
		
		assertEquals(expected, actualMovedUnits, () -> "Debe concatenar: " + movedUnits + 
													   + initialProduct.getId() +
													   " " + initialProduct.getUnits() +
													   " " + addedProduct.getUnits() + ",");
	}
	
	@Test
	@DisplayName("Debe retornar el producto con las unidades incrementadas")
	void testProcessProductInput() throws NoSuchMethodException, SecurityException,
								   IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String type = "Input";
		Product initialProduct = new Product(1, "Suit", "IronMan", "Marvel", 20);
		Product addedProduct = new Product(1, "Suit", "IronMan", "Marvel", 100);
		
		when(productRepo.findById(1)).thenReturn(Optional.of(initialProduct));
		
		final int expectedUnits = initialProduct.getUnits() + addedProduct.getUnits();
		
		Method processProduct = ProductServiceImpl.class.getDeclaredMethod("processProduct", Product.class, String.class);
		processProduct.setAccessible(true);
		
		final Product product = (Product) processProduct.invoke(productService, addedProduct, type);
		
		assertEquals(expectedUnits, product.getUnits(), "Debe sumar las unidades iniciales con las agregadas");
	}

	@Test
	@DisplayName("Debe retornar el producto con las unidades decrementadas")
	void testProcessProductOutput() throws NoSuchMethodException, SecurityException,
								   IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		String type = "Output";
		Product initialProduct = new Product(1, "Suit", "IronMan", "Marvel", 120);		
		Product addedProduct = new Product(1, "Suit", "IronMan", "Marvel", 10);
		
		when(productRepo.findById(1)).thenReturn(Optional.of(initialProduct));
		
		final int expectedUnits = initialProduct.getUnits() - addedProduct.getUnits();
		
		Method processProduct = ProductServiceImpl.class.getDeclaredMethod("processProduct", Product.class, String.class);
		processProduct.setAccessible(true);
		
		final Product product = (Product) processProduct.invoke(productService, addedProduct, type);
		
		assertEquals(expectedUnits, product.getUnits(), "Debe restar las unidades vendidas de las iniciales"); 
	}
}
