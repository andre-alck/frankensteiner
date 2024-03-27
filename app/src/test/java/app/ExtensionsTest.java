package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import app.exceptions.UnsupportedExtensionException;

class ExtensionsTest {

	@Test
	void givenStringThatMatchesElementOnEnum_whenCheckingIfTheMatch_thenShouldReturnTrue() {
		String validString = "SQL";
		assertTrue(Extensions.isElementExistent(validString));
	}

	@Test
	void givenStringThatDoesntMatchElementOnEnum_whenCheckingIfTheMatch_thenShouldReturnFalse() {
		String invalidString = "SQLZ";
		assertFalse(Extensions.isElementExistent(invalidString));
	}

	@ParameterizedTest
	@ValueSource(strings = { "SQL", "TXT" })
	void givenInput_whenCheckingEnumForElementsWithThatName_thenElementShouldExist(String input) {
		assertNotNull(Extensions.getElementBasedOnInputText(input));
	}

	@Test
	void givenInvalidInput_whenCheckingEnumForElementsWithThatName_thenShouldThrowException() {
		assertThrows(UnsupportedExtensionException.class, () -> {
			final String invalidInput = "nope";
			Extensions.getElementBasedOnInputText(invalidInput);
		});
	}

}
