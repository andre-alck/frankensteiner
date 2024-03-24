import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import app.Extensions;

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

}
