package org.dev.fhhf.hulkstore.controller;

import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/stock")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/{empId}/all")
	public String getAllProducts(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("products", productService.findAllProducts());
		model.addAttribute("empId", empId);
		return "products";
	}
	
	@GetMapping("/{empId}/initAdd")
	public String initAddtProduct(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("product", new Product());
		model.addAttribute("empId", empId);
		return "addProduct";
	}
	
	@PostMapping("/{empId}/addProduct")
	public String addtProduct(@PathVariable("empId") int empId, Product product) {
		product.setUnits(0);
		productService.saveProduct(product);
		return "redirect:/stock/" + empId + "/all";
	}
}
