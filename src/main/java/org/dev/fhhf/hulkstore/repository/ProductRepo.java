package org.dev.fhhf.hulkstore.repository;

import java.util.Optional;

import org.dev.fhhf.hulkstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product, Integer>{
	
	Optional<Product> findProductByItem(String item);
}
