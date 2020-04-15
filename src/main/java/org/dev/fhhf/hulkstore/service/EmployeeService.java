package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;

public interface EmployeeService {
	
	List<Employee> findAllEmployees();
	
	Employee findEmployeeById(int empId);
	
	Employee saveEmployee(Employee employee);
	
	void deleteEmployee(Employee employee);
}
