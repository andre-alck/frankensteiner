package app;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import app.exceptions.EmptyExtensionException;
import app.exceptions.UnsupportedExtensionException;

public class ExtensionServiceTest {
	@ParameterizedTest
	@ValueSource(strings = { StringUtils.DOT, StringUtils.WHITESPACE })
	void givenExtensionContainingOnlyWhitespaceOrDot_whenCheckingIfExtensionIsValid_shouldThrowException(
			String extension) {
		Exception e = assertThrows(EmptyExtensionException.class, () -> {
			ExtensionService.checkIfExtensionIsValid(extension);
		});

		assertTrue(e instanceof EmptyExtensionException);
	}

	@Test
	void givenUnsupportedExtension_whenCheckingSupport_shouldNotThrowException() {
		final String unsupportedExtension = "ABC";
		Exception e = assertThrows(UnsupportedExtensionException.class, () -> {
			ExtensionService.checkIfExtensionIsValid(unsupportedExtension);
		});

		assertTrue(e instanceof UnsupportedExtensionException);
	}

	@ParameterizedTest
	@ValueSource(strings = { "SQL", "TXT" })
	void givenSupportedExtension_whenCheckingSupport_shouldNotThrowException(String extension) {
		assertDoesNotThrow(() -> {
			ExtensionService.checkIfExtensionIsValid(extension);
		});
	}

	@Test
	void givenStringThatMatchesElementOnEnum_whenCheckingIfTheMatch_thenShouldReturnTrue() {
		String validString = "SQL";
		assertTrue(ExtensionService.isElementExistent(validString));
	}

	@Test
	void givenStringThatDoesntMatchElementOnEnum_whenCheckingIfTheMatch_thenShouldReturnFalse() {
		String invalidString = "SQLZ";
		assertFalse(ExtensionService.isElementExistent(invalidString));
	}

	@ParameterizedTest
	@ValueSource(strings = { "SQL", "TXT" })
	void givenInput_whenCheckingEnumForElementsWithThatName_thenElementShouldExist(String input) {
		assertNotNull(ExtensionService.getElementBasedOnInputText(input));
	}

	@Test
	void givenInvalidInput_whenCheckingEnumForElementsWithThatName_thenShouldThrowException() {
		assertThrows(UnsupportedExtensionException.class, () -> {
			final String invalidInput = "nope";
			ExtensionService.getElementBasedOnInputText(invalidInput);
		});
	}
}
