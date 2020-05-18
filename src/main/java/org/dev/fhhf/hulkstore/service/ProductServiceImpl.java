package org.dev.fhhf.hulkstore.service;

import java.util.ArrayList;
import java.util.List;

import org.dev.fhhf.hulkstore.exception.NotEnoughStockException;
import org.dev.fhhf.hulkstore.model.Movement;
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

	@Override
	public void getAddedProducts(Movement movement, String type) {

		List<Product> addedProducts = movement.getProducts();
		String movedUnits = "";
		
		movement.setProducts(new ArrayList<Product>());

		for (Product addedProduct : addedProducts) {
			
			if (addedProduct.getUnits() != 0) {
				
				movedUnits = updateMovedUnits(addedProduct, movedUnits);
				
				movement.addProduct(processProduct(addedProduct, type));
			}
		}
		
		movement.setMovedUnits(movedUnits);
	}
	
	private String updateMovedUnits(Product addedProduct, String movedUnits) {
		
		Product product = productRepo.findById(addedProduct.getId()).get();
		return movedUnits.concat(product.getId() + " " + product.getUnits() + " " + addedProduct.getUnits() + ",");
	}
	
	private Product processProduct(Product addedProduct, String type) {
		
		Product product = productRepo.findById(addedProduct.getId()).get();
		int units = 0;

		if (type.equals("Input")) {
			
			units = product.getUnits() + addedProduct.getUnits();
			
		} else if (type.equals("Output")) {
			
			units = product.getUnits() - addedProduct.getUnits();
			
			if (units <= 0) {
				throw new NotEnoughStockException(
						"No hay unidades suficientes para el producto: "+
						product.getItem()+" "+product.getHero()+" "+product.getBrand()+
						" Usted solicitó: " + addedProduct.getUnits() + ", hay disponibles: " + product.getUnits()
						);
			}
		}

		product.setUnits(units);		
		return product;
	}
}
