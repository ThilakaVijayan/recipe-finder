package main.java.au.com.thilaka.exception;

public class InputException extends Exception {

	private static final long serialVersionUID = 1L;

	private InputException(){}

	public InputException(final String message) {
		super(message);
	}

}
