package org.dev.fhhf.hulkstore.controller;

import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.service.ProductService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.*;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

import java.util.Arrays;

@RestClientTest
public class ProductControllerTest {

	@Mock
	private ProductService productService;
	
	@InjectMocks
	private ProductController productController;
	
	private MockMvc mockMvc;
	
	@BeforeEach
    void setUp() {
        mockMvc = standaloneSetup(productController).build();
    }
	
	@Test
	public void getAllProductsTest() throws Exception {
		Product product1 = new Product(1, "Suit", "IronMan", "Marvel", 100);
		Product product2 = new Product(2, "Hammer", "Thor", "Marvel", 50);
		Product product3 = new Product(3, "Cards", "Joker", "DC", 70);
		
		when(productService.findAllProducts())
        		.thenReturn(Arrays.asList(product1, product2, product3));
		
		mockMvc.perform(get("/stock/7/all"))
		        .andExpect(status().isOk())
		        .andExpect(view().name("products"))
		        .andExpect(model().attribute("products", hasSize(3)))
		        .andExpect(model().attribute("products", hasItem(
		                allOf(
		                        hasProperty("id", is(1)),
		                        hasProperty("item", is("Suit")),
		                        hasProperty("hero", is("IronMan")),
		                        hasProperty("brand", is("Marvel")),
		                        hasProperty("units", is(100))
		                )
		        )))
		        .andExpect(model().attribute("products", hasItem(
		                allOf(
		                        hasProperty("id", is(2)),
		                        hasProperty("item", is("Hammer")),
		                        hasProperty("hero", is("Thor")),
		                        hasProperty("brand", is("Marvel")),
		                        hasProperty("units", is(50))
		                )
		        )))
		        .andExpect(model().attribute("products", hasItem(
		                allOf(
		                        hasProperty("id", is(3)),
		                        hasProperty("item", is("Cards")),
		                        hasProperty("hero", is("Joker")),
		                        hasProperty("brand", is("DC")),
		                        hasProperty("units", is(70))
		                )
		        )))
		        .andExpect(model().attribute("empId", is(7)));
	}
}
