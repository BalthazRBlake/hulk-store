package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.model.Product;

public interface ProductService {

	List<Product> findAllProducts();
	
	Product findProductById(int itemId);
	
	Product findProductByItem(String item);
	
	//Save New or Update
	Product saveProduct(Product product);
	
	void deleteProduct(Product product);
	
	List<Product> getEmptyListOfProducts();
	
	void getAddedProducts(Movement movement, String type);
}
