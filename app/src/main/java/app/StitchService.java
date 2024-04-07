package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StitchService {
	protected abstract Extensions getExtension();

	//	public void stitch(String folderPath) {}
	//	protected abstract void writeFile();

	public List<FileData> readFiles(String folderPath) {
		File folder = new File(folderPath);
		List<File> allFilesOfGivenExtension = this.listFilesBasedOnExtension(folder);
		return this.getFileDataForEachFile(allFilesOfGivenExtension);
	}

	private List<File> listFilesBasedOnExtension(File folder) {
		File[] filesInGivenFolder = folder.listFiles();
		return Stream.of(filesInGivenFolder).filter(this.isFileExtensionEqualToClassImplementationExtension())
				.collect(Collectors.toList());
	}

	private Predicate<File> isFileExtensionEqualToClassImplementationExtension() {
		String extensionName = this.getExtension().name();
		return eachFile -> eachFile.getName().endsWith(extensionName.toUpperCase())
				|| eachFile.getName().endsWith(extensionName.toLowerCase());
	}

	private List<FileData> getFileDataForEachFile(List<File> files) {
		List<FileData> dataFromEachFile = new ArrayList<>();
		files.forEach(file -> {
			try {
				this.getFileDataFromFile(file, dataFromEachFile);
			} catch (Exception e) {
				Logger.log(e);
			}
		});
		
		return dataFromEachFile;
	}

	private void getFileDataFromFile(File file, List<FileData> dataFromEachFile) throws IOException {
		FileData fileData = new FileData();

		StringBuilder fileContent = this.readFileContent(new FileInputStream(file));
		fileData.setContent(fileContent);

		dataFromEachFile.add(fileData);
	}

	private StringBuilder readFileContent(InputStream inputStream) throws IOException {
		StringBuilder fileContent = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
			String line;
			while ((line = br.readLine()) != null) {
				fileContent.append(line).append("\n");
			}
		}
		return fileContent;
	}
}
