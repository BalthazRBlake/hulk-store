package org.dev.fhhf.hulkstore.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.model.Movement;
import org.dev.fhhf.hulkstore.model.Product;

import org.dev.fhhf.hulkstore.service.DateFormaterService;
import org.dev.fhhf.hulkstore.service.EmployeeService;
import org.dev.fhhf.hulkstore.service.MovementService;
import org.dev.fhhf.hulkstore.service.ProductService;
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
	private MovementService movementService;
	@Autowired
	private EmployeeService employeeService;
	@Autowired
	private ProductService productService;
	@Autowired
	private DateFormaterService dateService;

	/**
	 * Pone loas atributos en el modelo, transactions extrae la información
	 * detallada de cada movimiento
	 */
	@GetMapping("/{empId}/all")
	public String getAllMovements(@PathVariable("empId") int empId, Model model) {

		List<Movement> movements = movementService.findAllMovements();
		List<List<String>> transactions = new ArrayList<>();
		
		for (Movement m : movements) {
			transactions.add(Arrays.asList(m.getMovedUnits().split(",")));
		}

		model.addAttribute("transactions", transactions);
		model.addAttribute("moves", movements);
		model.addAttribute("empId", empId);
		return "moves";
	}

	/**
	 * Prepara el modelo para iniciar una operacion
	 * @param empId identidifica al empleado en sesion
	 * @param type identifica el tipo de operacion
	 */
	@GetMapping("/{empId}/init/{moveType}")
	public String initAddStock(@PathVariable("empId") int empId, @PathVariable("moveType") String type, Model model) {

		Date date = dateService.giveFormat(new Date());
		Employee employee = employeeService.findEmployeeById(empId);
		Movement movement = new Movement(date, type, employee);

		List<Product> products = new ArrayList<>();

		for (Product p : productService.findAllProducts()) {
			p.setUnits(0);
			products.add(p);
		}

		movement.setProducts(products);

		model.addAttribute("movement", movement);
		model.addAttribute("empId", empId);

		return "addStock";
	}

	/**
	 * 
	 */
	@PostMapping("/{empId}/{moveType}/Products")
	public String executeOperation(@PathVariable("empId") int empId, @PathVariable("moveType") String type,
			Movement movement) {

		List<Product> addedProducts = (List<Product>) movement.getProducts();
		String movedUnits = "";
		movement.setProducts(new ArrayList<Product>());

		for (Product aP : addedProducts) {
			if (aP.getUnits() != 0) {
				
				Product pro = productService.findProductById(aP.getId());

				movedUnits = movedUnits
						.concat(pro.getId() + " _ _ _ " + pro.getUnits() + " _ _ _ " + aP.getUnits() + ",");
				int units = 0;

				if (type.equals("Input")) {
					units = pro.getUnits() + aP.getUnits();
				} else if (type.equals("Output")) {
					units = pro.getUnits() - aP.getUnits();
					if (units <= 0) {
						return "redirect:/move/" + empId + "/all";
					}
				}

				pro.setUnits(units);
				movement.addProduct(pro);
			}
		}

		movement.setMovedUnits(movedUnits);

		movementService.saveMovement(movement);
		return "redirect:/move/" + empId + "/all";
	}

}
