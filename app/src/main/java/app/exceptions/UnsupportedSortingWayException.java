package app.exceptions;

public class UnsupportedSortingWayException extends FrankesteinerCommonException {

	private static final long serialVersionUID = 1L;

	public UnsupportedSortingWayException() {
	}

	public UnsupportedSortingWayException(String message, Throwable cause) {
		super(message, cause);
	}

}
