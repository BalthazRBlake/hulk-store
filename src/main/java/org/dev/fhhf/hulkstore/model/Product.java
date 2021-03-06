package org.dev.fhhf.hulkstore.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.core.style.ToStringCreator;
/**
 * JavaBean domain object representing a Product.
 * For the initial test item, brand and hero are single strings,
 * this can be injected entities in future.
 */
@Entity
@Table(name = "products")
public class Product extends BasicEntity{

	private static final long serialVersionUID = -4179004278346701103L;
	
	@NotEmpty
	private String item;

	@NotEmpty
	private String hero;
	
	@NotEmpty
	private String brand;

	private Integer units;
	
	@ManyToMany(mappedBy = "products")
	private List<Movement> movements = new ArrayList<>();
	
	public Product() {
		super();
	}
	
	public Product(Integer id, @NotEmpty String item, @NotEmpty String hero, @NotEmpty String brand, Integer units) {
		super(id);
		this.item = item;
		this.hero = hero;
		this.brand = brand;
		this.units = units;
	}

	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getHero() {
		return hero;
	}
	public void setHero(String hero) {
		this.hero = hero;
	}
	public Integer getUnits() {
		return units;
	}
	public void setUnits(Integer units) {
		this.units = units;
	}
	public List<Movement> getMovements() {
		return movements;
	}
	public void setMovements(List<Movement> movements) {
		this.movements = movements;
	}
	
	@Override
	public String toString() {
		return new ToStringCreator(this)
	            .append("id", this.getId())
	            .append("item", this.item)
	            .append("brand", this.brand)
	            .append("hero", this.hero)
	            .append("units", this.units)
	            .toString();
	}
}
