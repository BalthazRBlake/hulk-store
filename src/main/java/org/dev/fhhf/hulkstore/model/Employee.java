package org.dev.fhhf.hulkstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.core.style.ToStringCreator;
/**
 * JavaBean domain object representing a Employee.
 */
@Entity
@Table(name = "employees")
public class Employee extends Person{

	private static final long serialVersionUID = -2544890317834735185L;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
	private List<Movement> movements = new ArrayList<>();
	
	public List<Movement> getMovements() {
		return movements;
	}

	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}

	public void addMovement(Movement movement) {
		this.getMovements().add(movement);
	}
	@Override
	public String toString() {
		return new ToStringCreator(this)
	            .append("Id", this.getId())
	            .append("Name", this.getFirstName())
	            .toString();
	}
}
