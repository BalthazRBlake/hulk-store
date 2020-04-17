package org.dev.fhhf.hulkstore.exception;

public class ParseDateFormatException extends RuntimeException{

	private static final long serialVersionUID = 9146265717829195149L;

	public ParseDateFormatException(String message) {
        super(message);
    }

    public ParseDateFormatException(String message, Throwable cause) {
        super(message, cause);
    }
}
