package org.dev.fhhf.hulkstore.service;

import java.util.ArrayList;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepo productRepo;
	
	@Override
	public List<Product> findAllProducts() {
		return productRepo.findAll();
	}
	
	@Override
	public Product findProductById(int itemId) {
		return productRepo.findById(itemId).get();
	}

	@Override
	public Product findProductByItem(String item) {
		return productRepo.findProductByItem(item).get();
	}
	
	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}

	@Override
	public void deleteProduct(Product product) {
		productRepo.delete(product);
	}

	@Override
	public List<Product> getEmptyListOfProducts() {
		List<Product> products = new ArrayList<>();

		for (Product p : productRepo.findAll()) {
			p.setUnits(0);
			products.add(p);
		}
		return products;
	}
	
	
}
