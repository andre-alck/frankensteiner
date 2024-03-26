package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class PathServiceTest {

	@Test
	void givenSearchForPath_whenPathExists_thenReturnTrue() {
		final String existingPath = "/home/linkedrh/Desktop";
		assertTrue(PathService.isPathExistent(existingPath));
	}

	@Test
	void givenSearchForPath_whenPathDoesNotExist_thenReturnFalse() {
		final String nonExistingPath = "testfolder";
		assertFalse(PathService.isPathExistent(nonExistingPath));
	}

	@Test
	void givenSearchForExtensionInPath_whenThereAreAtLeastTwoFilesWithThatExtension_thenReturnTrue() {
		final String extension = "sql";
		assertTrue(PathService.isFileCountWithExtensionMoreThanOne(extension,
				"/home/linkedrh/Desktop/PathServiceTest/givenSearchForExtensionInPath_whenThereAreAtLeastTwoFilesWithThatExtension_thenReturnTrue"));
	}

	@ParameterizedTest
	@ValueSource(strings = { "txt", "nonexistent" })
	void givenSearchForExtensionInPath_whenThereAreNotAtLeastTwoFilesWithThatExtension_thenReturnFalse(
			String extension) {
		assertFalse(PathService.isFileCountWithExtensionMoreThanOne(extension,
				"/home/linkedrh/Desktop/PathServiceTest/givenSearchForExtensionInPath_whenThereAreNotAtLeastTwoFilesWithThatExtension_thenReturnFalse/"));
	}
}
