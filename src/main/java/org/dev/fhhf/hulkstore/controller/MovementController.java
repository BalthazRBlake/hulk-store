package org.dev.fhhf.hulkstore.controller;

import java.util.ArrayList;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.repository.MovementRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MovementController {

	@Autowired
	private MovementRepo moveRepo;
	
	@GetMapping("{empId}/initAdd")
	public String initAddStock(@PathVariable("empId") int empId, Model model) {
		Movement movement = new Movement();
		List<Product> products = new ArrayList<>();
		
		model.addAttribute("movement", movement);
		model.addAttribute("products", products);
		model.addAttribute("empId", empId);
		return "addStock";
	}
}
