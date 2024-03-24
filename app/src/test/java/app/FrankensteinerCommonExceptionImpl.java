package app;

import app.exceptions.FrankesteinerCommonException;

public class FrankensteinerCommonExceptionImpl extends FrankesteinerCommonException {
	public FrankensteinerCommonExceptionImpl() {
	}

	public FrankensteinerCommonExceptionImpl(String message, Throwable cause) {
		super(message, cause);
	}
}
