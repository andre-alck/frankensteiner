package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import exceptions.FrankensteinerCommonExceptionFactory;
import exceptions.FrankesteinerCommonException;

class FrankensteinerCommonExceptionFactoryTest {

	@Test
	void givenEmptyMessageAndEmptyCause_whenInstanciatingFrankesteinerCommonExceptionImpl_shouldHaveDefaultMessage() {
		String emptyMessage = null;
		Throwable emptyCause = null;

		FrankensteinerCommonExceptionImpl e = (FrankensteinerCommonExceptionImpl) FrankensteinerCommonExceptionFactory
				.newException(emptyMessage, emptyCause, FrankensteinerCommonExceptionImpl.class);

		assertEquals(e.getMessage(), FrankesteinerCommonException.MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED);
	}

	@Test
	void givenEmptyCause_whenInstanciatingFrankesteinerCommonExceptionImpl_shouldHaveDefaultMessage() {
		String nonEmptyMessage = "Non-empty message";
		Throwable emptyCause = null;

		FrankensteinerCommonExceptionImpl e = (FrankensteinerCommonExceptionImpl) FrankensteinerCommonExceptionFactory
				.newException(nonEmptyMessage, emptyCause, FrankensteinerCommonExceptionImpl.class);

		assertEquals(e.getMessage(), FrankesteinerCommonException.MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED);
	}

	@Test
	void givenEmptyMessage_whenInstanciatingFrankesteinerCommonExceptionImpl_shouldHaveDefaultMessage() {
		String emptyMessage = null;
		Throwable nonEmptyCause = new Throwable();

		FrankensteinerCommonExceptionImpl e = (FrankensteinerCommonExceptionImpl) FrankensteinerCommonExceptionFactory
				.newException(emptyMessage, nonEmptyCause, FrankensteinerCommonExceptionImpl.class);

		assertEquals(e.getMessage(), FrankesteinerCommonException.MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED);
	}

	@Test
	void givenNotEmptyMessageAndEmptyCause_whenInstanciatingFrankesteinerCommonExceptionImpl_shouldNotHaveDefaultMessage() {
		String emptyMessage = "Non-empty message";
		Throwable nonEmptyCause = new Throwable();

		FrankensteinerCommonExceptionImpl e = (FrankensteinerCommonExceptionImpl) FrankensteinerCommonExceptionFactory
				.newException(emptyMessage, nonEmptyCause, FrankensteinerCommonExceptionImpl.class);

		assertNotEquals(e.getMessage(), FrankesteinerCommonException.MESSAGE_OR_CAUSE_OF_EXCEPTION_UNDEFINED);
		assertNotNull(e.getCause());
	}

}
