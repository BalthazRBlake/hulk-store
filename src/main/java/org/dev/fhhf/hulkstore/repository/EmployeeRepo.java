package org.dev.fhhf.hulkstore.repository;
import org.dev.fhhf.hulkstore.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
