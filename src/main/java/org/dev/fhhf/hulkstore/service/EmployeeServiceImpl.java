package org.dev.fhhf.hulkstore.service;

import java.util.List;

import org.dev.fhhf.hulkstore.model.Employee;
import org.dev.fhhf.hulkstore.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	private EmployeeRepo empRepo;
	
	@Override
	public List<Employee> findAllEmployees() {
		return empRepo.findAll();
	}

	@Override
	public Employee findEmployeeById(int empId) {
		return empRepo.findById(empId).get();
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		return empRepo.save(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		empRepo.delete(employee);
	}	
}
