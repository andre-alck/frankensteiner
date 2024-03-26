package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import app.exceptions.InexistentPathException;
import app.exceptions.InsufficientAmountOfFiles;

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
	void givenSearchForExtensionInPath_whenPathDoesNotExist_thenShouldThrowException() {
		assertThrows(InexistentPathException.class, () -> {
			PathService.isFileCountWithExtensionMoreThanOne(null, "/home/linkedrh/Desktop/PathServiceTest/fakepath");
		});
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
				"/home/linkedrh/Desktop/PathServiceTest/givenSearchForExtensionInPath_whenThereAreNotAtLeastTwoFilesWithThatExtension_thenReturnFalse"));
	}

	@Test
	void givenFileCountExtension_whenPathDoesNotHaveMoreThanOneFileWithThatExtensoin_thenShouldThrowException() {
		assertThrows(InsufficientAmountOfFiles.class, () -> {
			PathService.checkIfPathIsValid("txt",
					"/home/linkedrh/Desktop/PathServiceTest/givenSearchForExtensionInPath_whenThereAreNotAtLeastTwoFilesWithThatExtension_thenReturnFalse");
		});
	}
}
