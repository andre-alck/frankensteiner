package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StitchService {
	protected abstract Extensions getExtension();

	protected abstract void writeFile(List<FileData> filesData, SortingWay sortingWay);

	public String getResultFileName() {
		String name = this.getFormattedDate() + StringUtils.DOT + this.getExtension().name().toLowerCase();
		String validatedName = this.replaceInvalidCharacters(name);
		return validatedName;
	}

	protected String getFormattedDate() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	private String replaceInvalidCharacters(String content) {
		String validatedName = content.replaceAll(":", "h").replaceAll("/", "_");
		return validatedName;
	}

	protected String getConcatenatedStringThroughFileData(List<FileData> filesData) {
		return filesData.stream().map(FileData::getContent).collect(Collectors.joining()).replace("\n", "");
	}
	
	public void sort(List<FileData> filesData, SortingWay sortingWay) {
		filesData.forEach(f -> f.setSortingWay(sortingWay));
		Collections.sort(filesData);
	}

	public void stitch(String folderPath, SortingWay sortingWay) {
		List<FileData> filesData = this.readFiles(folderPath);
		this.writeFile(filesData, sortingWay);
	}

	protected List<FileData> readFiles(String folderPath) {
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
		return eachFile -> eachFile.getName().toLowerCase().endsWith(extensionName.toLowerCase());
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
		fileData.setName(file.getName());
		fileData.setModificationDate(new Date(file.lastModified()).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
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
