package org.dev.fhhf.hulkstore.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
/**
 * Can be Input, Output, Return, Sale...
 */
@Entity
@Table(name = "move_types")
public class MoveType extends BasicEntity{
	
	private static final long serialVersionUID = -8060905322922407030L;
	
	@Column(name = "name")
	@NotEmpty
	private String type;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "MoveType [type=" + type + "]";
	}
}
