package app.exceptions;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import app.Logger;
import app.StringUtils;

public class FrankensteinerCommonExceptionFactory {

	public static FrankesteinerCommonException newException(String message, Throwable cause,
			Class<? extends FrankesteinerCommonException> clazz) {
		boolean isExceptionInformationIncomplete = StringUtils.isContentEmpty(message)
				|| StringUtils.isContentEmpty(cause);
		return FrankensteinerCommonExceptionFactory.getInstance(message, cause, clazz,
				isExceptionInformationIncomplete);
	}

	private static FrankesteinerCommonException getInstance(String message, Throwable cause,
			Class<? extends FrankesteinerCommonException> clazz, boolean isExceptionInformationIncomplete) {
		try {
			return FrankensteinerCommonExceptionFactory.getInstanceBasedOnConstructor(message, cause, clazz,
					isExceptionInformationIncomplete);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			Logger.log(e);
		}

		return null;
	}

	private static FrankesteinerCommonException getInstanceBasedOnConstructor(String message, Throwable cause,
			Class<? extends FrankesteinerCommonException> clazz, boolean isExceptionInformationIncomplete)
			throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Constructor<?> exceptionConstructor = null;
		FrankesteinerCommonException exception = null;

		if (isExceptionInformationIncomplete) {
			exceptionConstructor = FrankensteinerCommonExceptionFactory.getNoArgsConstructor(clazz);
			exception = (FrankesteinerCommonException) exceptionConstructor.newInstance((Object[]) null);
		} else {
			exceptionConstructor = FrankensteinerCommonExceptionFactory.getMessageAndCauseConstructor(clazz);
			exception = (FrankesteinerCommonException) exceptionConstructor.newInstance(message, cause);
		}

		return exception;
	}

	private static Constructor<?> getNoArgsConstructor(Class<? extends FrankesteinerCommonException> clazz) {
		Constructor<?> c = null;

		try {
			c = clazz.getConstructor();
		} catch (NoSuchMethodException e) {
			Logger.log(e);
		}

		return c;
	}

	private static Constructor<?> getMessageAndCauseConstructor(Class<? extends FrankesteinerCommonException> clazz) {
		Constructor<?> c = null;

		try {
			c = clazz.getConstructor(String.class, Throwable.class);
		} catch (NoSuchMethodException e) {
			Logger.log(e);
		}

		return c;
	}

}
