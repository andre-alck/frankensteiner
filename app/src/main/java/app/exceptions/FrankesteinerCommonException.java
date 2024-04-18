package app.exceptions;

public abstract class FrankesteinerCommonException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public static final String MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED = "Message or cause of exception undefined.";

	public FrankesteinerCommonException(String message, Throwable cause) {
		super(message, cause);
	}

	public FrankesteinerCommonException() {
		super(FrankesteinerCommonException.MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED);
	}
}
