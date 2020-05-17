package org.dev.fhhf.hulkstore.model;

public class MovedUnits {

	private int productId;
	private int initialUnits;
	private int movedUnits;
	
	public MovedUnits() {
		super();
	}
	
	public MovedUnits(int productId, int initialUnits, int movedUnits) {
		super();
		this.productId = productId;
		this.initialUnits = initialUnits;
		this.movedUnits = movedUnits;
	}
	
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getInitialUnits() {
		return initialUnits;
	}
	public void setInitialUnits(int initialUnits) {
		this.initialUnits = initialUnits;
	}
	public int getMovedUnits() {
		return movedUnits;
	}
	public void setMovedUnits(int movedUnits) {
		this.movedUnits = movedUnits;
	}
	
	@Override
	public String toString() {
		return "MovedUnits [productId=" + productId + ", initialUnits=" + initialUnits + ", movedUnits=" + movedUnits
				+ "]";
	}
}
