package app.exceptions;

public class InsufficientAmountOfFiles extends FrankesteinerCommonException {
	private static final long serialVersionUID = 1L;

	public InsufficientAmountOfFiles() {
	}

	public InsufficientAmountOfFiles(String message, Throwable cause) {
		super(message, cause);
	}
}
