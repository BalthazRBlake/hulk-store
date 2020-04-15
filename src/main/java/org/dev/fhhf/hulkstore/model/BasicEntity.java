package org.dev.fhhf.hulkstore.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
/**
 * JavaBean domain object with an id property. Used as the base class for objects
 * needing this property.
 */
@MappedSuperclass
public class BasicEntity implements Serializable {
	
	private static final long serialVersionUID = 1199210837345843643L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public boolean isNew() {
		return this.id == null;
	}
}
