package app.exceptions;

public class InexistentPathException extends FrankesteinerCommonException {
	private static final long serialVersionUID = 1L;

	public InexistentPathException() {
	}

	public InexistentPathException(String message, Throwable cause) {
		super(message, cause);
	}
}
