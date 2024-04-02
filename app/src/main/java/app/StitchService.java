package app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class StitchService {
	protected abstract Extensions getExtension();

	public List<FileData> readFiles(String folderPath) {
		File folder = new File(folderPath);
		List<File> files = this.listFilesBasedOnExtension(folder);
		return this.fillList(files);
	}

	private List<File> listFilesBasedOnExtension(File folder) {
		return Stream.of(folder.listFiles()).filter(eachFile -> eachFile.getName().endsWith(this.getExtension().name()))
				.collect(Collectors.toList());
	}

	private List<FileData> fillList(List<File> files) {
		List<FileData> listContainingDataFromEachFile = new ArrayList<>();
		files.forEach(t -> {
			try {
				StringBuilder content = this.readFileContent(new FileInputStream(t));
				FileData f = new FileData();
				f.setContent(content);
				listContainingDataFromEachFile.add(null);
			} catch (Exception e) {
				Logger.log(e);
			}
		});

		return listContainingDataFromEachFile;
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
