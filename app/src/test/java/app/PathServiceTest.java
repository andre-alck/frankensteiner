package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PathServiceTest {

	@Test
	void givenSearchForPath_whenPathExists_thenReturnTrue() {
		final String existingPath = "/home/linkedrh/Desktop";

		assertTrue(PathService.isPathExistent(existingPath));
	}

	@Test
	void givenSearchForPath_whenPathDoesntExists_thenReturnFalse() {
		final String nonExistingPath = "testfolder";

		assertFalse(PathService.isPathExistent(nonExistingPath));
	}
}
