package org.dev.fhhf.hulkstore.controller;

import org.dev.fhhf.hulkstore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	@GetMapping("/all")
	public String getAllEmployees(Model model) {
		model.addAttribute("employees", empService.findAllEmployees());
		return "employees";
	}

	/*
	 * Pone al Empleado en sessión 
	 */
	@GetMapping("/{empId}/initMove")
	public String initMovement(@PathVariable("empId") int empId, Model model) {
		model.addAttribute("employee", empService.findEmployeeById(empId));
		return "movements";
	}
}
