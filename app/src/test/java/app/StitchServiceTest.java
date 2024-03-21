package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class StitchServiceTest {

	@Test
	void givenExtensionWithDot_whenRemoveDotIfNeededMethodIsCalled_thenShouldRemoveTheDot() {
		final String dot = ".";
		final String extension = "abc";
		StitchService s = new StitchServiceImpl(dot + extension, null);

		s.removeDotIfNeeded();

		assertEquals(extension, s.getExtension());
	}

	@Test
	void givenExtensionWithNoDot_whenRemoveDotIfNeededMethodIsCalled_thenShouldReturn() {
		final String extension = "abc";
		StitchService s = new StitchServiceImpl(extension, null);

		s.removeDotIfNeeded();

		assertEquals(extension, s.getExtension());
	}

	@Test
	void givenUnsupportedExtension_whenIsExtensionUnsupportedMethodIsCalled_thenShouldReturnTrue() {
		final String extension = "abc";
		StitchService s = new StitchServiceImpl(extension, null);

		assertTrue(s.isExtensionUnsupported());
	}

}
