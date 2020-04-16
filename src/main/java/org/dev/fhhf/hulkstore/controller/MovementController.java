package org.dev.fhhf.hulkstore.controller;

import java.util.ArrayList;
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
		System.out.println(moveRepo.findAll());
		model.addAttribute("moves", moveRepo.findAll());
		return "moves";
	}
	@GetMapping("/{empId}/initAdd")
	public String initAddStock(@PathVariable("empId") int empId, Model model) {
		
		Date date = dateService.giveFormat(new Date());		
		Employee employee = empRepo.findById(empId).get();
		Movement movement = new Movement(date, "Input", employee);
		
		List<Product> products = new ArrayList<>();
		
		for(Product p : productRepo.findAll()) {
			p.setUnits(0);
			products.add(p);
		}
		
		movement.setProducts(products);
		
		model.addAttribute("movement", movement);
		model.addAttribute("empId", empId);
		return "addStock";
	}
	
	@PostMapping("/{empId}/addProducts")
	public String executeInput(@PathVariable("empId") int empId, Movement movement) {

		List<Product> addedProducts = (List<Product>) movement.getProducts();
		String movedUnits = "";
		movement.setProducts(new ArrayList<Product>());
		
		System.out.println(addedProducts);
		
		for(Product aP : addedProducts){
			if(aP.getUnits() != 0) {
				Product pro = productRepo.findById(aP.getId()).get();
				
				movedUnits = movedUnits.concat(pro.getId() +" "+ pro.getUnits() +" "+ aP.getUnits());
				
				int units = pro.getUnits() + aP.getUnits();
				pro.setUnits(units);
				movement.addProduct(pro);
			}
		}
		//System.out.println(movement.getProducts());
		movement.setMovedUnits(movedUnits);
		moveRepo.save(movement);
		return "redirect:/move/all";
	}
	
}
