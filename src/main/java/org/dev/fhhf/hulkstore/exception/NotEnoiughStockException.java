package org.dev.fhhf.hulkstore.exception;

public class NotEnoiughStockException extends RuntimeException {

	private static final long serialVersionUID = -8323980118389832406L;

	public NotEnoiughStockException(String message) {
		super(message);
	}
	
	public NotEnoiughStockException(String message, Throwable ex) {
		super(message, ex);
	}
}
