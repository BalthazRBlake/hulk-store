package org.dev.fhhf.hulkstore.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
/**
 * JavaBean domain object representing a Person with firstName property.
 */
@MappedSuperclass
public class Person extends BasicEntity {
 
	private static final long serialVersionUID = 858602623757443658L;
	
	@Column(name = "first_name")
	@NotEmpty
 	private String firstName;

	public Person() {
		super();
	}

	public Person(Integer id, @NotEmpty String firstName) {
		super(id);
		this.firstName = firstName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
}
