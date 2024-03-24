package app;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathService {

	public static boolean isPathExistent(String path) {
		Path directoryPath = Paths.get(path);
		return Files.isDirectory(directoryPath);
	}

}
