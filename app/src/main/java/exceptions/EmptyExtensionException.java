package exceptions;

public class EmptyExtensionException extends FrankesteinerCommonException {
	private static final long serialVersionUID = 1L;

	public EmptyExtensionException() {
	}

	public EmptyExtensionException(String message, Throwable cause) {
		super(message, cause);
	}
}
