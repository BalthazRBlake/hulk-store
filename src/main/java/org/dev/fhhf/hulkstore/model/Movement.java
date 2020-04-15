package org.dev.fhhf.hulkstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.core.style.ToStringCreator;
import org.springframework.format.annotation.DateTimeFormat;
/**
 * JavaBean domain object representing a Movement input/output
 * of product stock.
 */
@Entity
@Table(name = "movements")
public class Movement extends BasicEntity{

	private static final long serialVersionUID = 8037140404143139687L;
	
	@Temporal(TemporalType.TIMESTAMP)
    @DateTimeFormat(pattern = "yyyy/MM/dd")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name = "type_id")
	private MoveType type;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@ManyToMany(mappedBy = "movements")
	private List<Product> products = new ArrayList<>();
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	public MoveType getType() {
		return type;
	}
	public void setType(MoveType type) {
		this.type = type;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
	            .append("Id", this.getId())
	            .append("Date", this.date)
	            .append("Type", this.getType())
	            .append("Emp", this.getEmployee())
	            .append("Products", this.getProducts())
	            .toString();
	}
}
