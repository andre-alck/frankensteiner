package app;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PathManagerTest {

	@Test
	void givenSearchForPath_whenPathExists_thenReturnTrue() {
		PathManager p = new PathManager();
		String existingPath = "/home/linkedrh/Desktop";

		boolean isPathExistent = p.isPathExistent(existingPath);

		assertTrue(isPathExistent);
	}

	@Test
	void givenSearchForPath_whenPathDoesntExists_thenReturnFalse() {
		PathManager p = new PathManager();
		String existingPath = "testfolder";

		boolean isPathExistent = p.isPathExistent(existingPath);

		assertFalse(isPathExistent);
	}
}
