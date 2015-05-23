package main.java.au.com.thilaka.vo;

public class Response {
	private String errorMessage;
	private String message;
	private boolean success;

	public String getErrorMessage() {
		return this.errorMessage;
	}

	public String getMessage() {
		return this.message;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setErrorMessage(final String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public void setMessage(final String message) {
		this.message = message;
	}

	public void setSuccess(final boolean success) {
		this.success = success;
	}

}
