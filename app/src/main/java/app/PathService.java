package app;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathService {

	public static boolean isPathExistent(String path) {
		Path directoryPath = Paths.get(path);
		return Files.isDirectory(directoryPath);
	}

	public static boolean isFileCountWithExtensionMoreThanOne(String extension, String path) {
		File folder = new File(path);
		FileFilter fileFilter = PathService.getFileFilter(extension);
		File[] files = folder.listFiles(fileFilter);

		int fileCount = 0;
		for (int i = 0; i < files.length; i++) {
			if (files[i].getName().endsWith(extension)) {
				fileCount++;
			}
		}

		return fileCount > 1;
	}

	private static FileFilter getFileFilter(String extension) {
		return new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getName().endsWith(extension);
			}
		};
	}

}
