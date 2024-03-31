package app;

import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import app.exceptions.InexistentPathException;
import app.exceptions.InsufficientAmountOfFiles;

public class PathService {

	public static void checkIfPathIsValid(String extension, String path) {
		if (!isFileCountWithExtensionMoreThanOne(extension, path)) {
			throw new InsufficientAmountOfFiles();
		}
	}

	protected static boolean isFileCountWithExtensionMoreThanOne(String extension, String path) {
		if (!PathService.isPathExistent(path)) {
			throw new InexistentPathException();
		}

		File folder = new File(path);
		FileFilter fileFilter = PathService.getFileFilter(extension);
		File[] files = folder.listFiles(fileFilter);

		return files.length > 1;
	}

	protected static boolean isPathExistent(String path) {
		Path directoryPath = Paths.get(path);
		return Files.isDirectory(directoryPath);
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
