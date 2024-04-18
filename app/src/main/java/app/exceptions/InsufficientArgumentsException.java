package app.exceptions;

public class InsufficientArgumentsException extends FrankesteinerCommonException {

	private static final long serialVersionUID = 1L;

	public InsufficientArgumentsException() {
	}

	public InsufficientArgumentsException(String message, Throwable cause) {
		super(message, cause);
	}

}
