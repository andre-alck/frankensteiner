package app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathManager {

	public boolean isPathExistent(String path) {
		Path directoryPath = Paths.get(path);
		return Files.isDirectory(directoryPath);
	}

}
