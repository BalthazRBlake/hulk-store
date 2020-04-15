package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Product;

public interface ProductService {

	List<Product> findAllProducts();
	
	Product findProductByItem(String item);
	
	//Save New or Update
	Product saveProduct(Product product);
	
	void deleteProduct(Product product);
}
