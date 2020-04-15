package org.dev.fhhf.hulkstore.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.model.MoveType;
import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.model.Product;
import org.dev.fhhf.hulkstore.repository.EmployeeRepo;
import org.dev.fhhf.hulkstore.repository.MovementRepo;
import org.dev.fhhf.hulkstore.repository.ProductRepo;
import org.dev.fhhf.hulkstore.service.DateFormaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/move")
public class MovementController {

	@Autowired
	private MovementRepo moveRepo;
	@Autowired
	private EmployeeRepo empRepo;
	@Autowired
	private ProductRepo productRepo;
	@Autowired
	private DateFormaterService dateService;
	
	@GetMapping("/{empId}/initAdd")
	public String initAddStock(@PathVariable("empId") int empId, Model model) {
		
		Date date = dateService.giveFormat(new Date());		
		MoveType type = new MoveType("Input");
		Employee employee = empRepo.findById(empId).get();
		Movement movement = new Movement(date, type, employee);
		List<Product> products = productRepo.findAll();
		movement.setProducts(products);
		
		model.addAttribute("movement", movement);
		//model.addAttribute("productsList", products);
		model.addAttribute("empId", empId);
		return "addStock";
	}
	
	@PostMapping("/{empId}/addProducts")
	public String executeInput(@PathVariable("empId") int empId, Movement movement) {
		
		System.out.println(movement);
		return "products";
	}
	
}
