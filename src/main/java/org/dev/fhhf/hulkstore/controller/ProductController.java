package org.dev.fhhf.hulkstore.controller;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@GetMapping("{empId}/all")
	public String getProductByName(@PathVariable("empId") int empId, Model model) {
		List<Product> products = productService.findAllProducts();
		model.addAttribute("products", products);
		model.addAttribute("empId", empId);
		return "products";
	}
}
