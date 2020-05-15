package org.dev.fhhf.hulkstore.exception;

public class NotEnoughStockException extends RuntimeException {

	private static final long serialVersionUID = -8323980118389832406L;

	public NotEnoughStockException(String message) {
		super(message);
	}
	
	public NotEnoughStockException(String message, Throwable ex) {
		super(message, ex);
	}
}
