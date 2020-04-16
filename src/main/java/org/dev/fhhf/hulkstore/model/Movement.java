package org.dev.fhhf.hulkstore.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
    @DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date date;
	
	/*Holds codified productId initial Units movedUnits for each
	*product in the transaction
	*/
	@Column(name = "moved_units")
	private String movedUnits;
	
	private String type;
	
	@ManyToOne
	@JoinColumn(name = "emp_id")
	private Employee employee;
	
	@ManyToMany(cascade = {
	        CascadeType.PERSIST,
	        CascadeType.MERGE
	    })//, fetch = FetchType.EAGER
    @JoinTable(
            name = "product_movement",
    		joinColumns = @JoinColumn(name = "movement_id"), 
            inverseJoinColumns = @JoinColumn(name = "product_id")
        )
	private List<Product> products = new ArrayList<>();
	
	public Movement() {
		super();
	}

	public Movement(Date date, String type, Employee employee) {
		super();
		this.date = date;
		this.type = type;
		this.employee = employee;
	}
	
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
	public String getMovedUnits() {
		return movedUnits;
	}
	public void setMovedUnits(String movedUnits) {
		this.movedUnits = movedUnits;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Product> getProducts() {
		return products;
	}
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	public void addProduct(Product product) {
		products.add(product);
		product.getMovements().add(this);
	}
	public void addRemove(Product product) {
		products.remove(product);
		product.getMovements().remove(this);
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
	            .append("Id", this.getId())
	            .append("Date", this.date)
	            .append("Type", this.getType())
	            .append("MovedUnits", this.movedUnits)
	            .append("Emp", this.getEmployee())
	            .append("Products", this.getProducts())
	            .toString();
	}
}
