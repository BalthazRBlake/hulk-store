package org.dev.fhhf.hulkstore.controller;

import java.util.Date;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
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
	
	@GetMapping("/all")
	public String getAllInputMoves(Model model) {
		model.addAttribute("moves", moveRepo.findAll());
		return "moves";
	}
	@GetMapping("/{empId}/initAdd")
	public String initAddStock(@PathVariable("empId") int empId, Model model) {
		
		Date date = dateService.giveFormat(new Date());		
		Employee employee = empRepo.findById(empId).get();
		Movement movement = new Movement(date, "Input", employee);
		
		List<Product> products = productRepo.findAll();
		for(Product p : products) {
			p.setUnits(0);
		}
		
		movement.setProducts(products);
		
		model.addAttribute("movement", movement);
		model.addAttribute("empId", empId);
		return "addStock";
	}
	
	@PostMapping("/{empId}/addProducts")
	public String executeInput(@PathVariable("empId") int empId, Movement movement) {
		//System.out.println(movement);
		//System.out.println(movement.getType());
		List<Product> products = productRepo.findAll();
		List<Product> addedProducts = movement.getProducts();
		//System.out.println("Antes   :::   ");
		//System.out.println(products);
		
		for(int i = 0; i < products.size(); i++) {
			Product p = addedProducts.get(i);
			
			if(p.getUnits() != 0 && p.getId() == products.get(i).getId()) {
				int units = products.get(i).getUnits() + p.getUnits();
				products.get(i).setUnits(units);
				productRepo.save(products.get(i));
			}
		}
		
		//System.out.println("Despues   :::   ");
		//System.out.println(products);
		moveRepo.save(movement);
		return "products";
	}
	
}
