package app;

import exceptions.FrankesteinerCommonException;

public class FrankensteinerCommonExceptionImpl extends FrankesteinerCommonException {
	public FrankensteinerCommonExceptionImpl() {
	}

	public FrankensteinerCommonExceptionImpl(String message, Throwable cause) {
		super(message, cause);
	}
}
