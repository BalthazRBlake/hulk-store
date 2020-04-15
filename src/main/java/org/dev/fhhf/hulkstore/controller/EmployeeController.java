package org.dev.fhhf.hulkstore.controller;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService empService;
	
	@GetMapping("/all")
	public String getAllEmployees(Model model) {
		List<Employee> employees = empService.findAllEmployees();
		model.addAttribute("employees", employees);
		return "employees";
	}
}
