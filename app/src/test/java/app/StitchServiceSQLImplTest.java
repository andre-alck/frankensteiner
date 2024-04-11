package app;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;


class StitchServiceSQLImplTest {

	@Test
	void givenCallForStitchingMethod_whenFileCreationIsChecked_thenShouldReturnTrue() throws IOException {
		StitchService stitchService = new StitchServiceSQLImpl();

		List<FileData> filesData = this.getExampleContentFromEachFile();
		stitchService.writeFile(filesData, new AlphabeticalSorting());

		final String resultFileName = stitchService.getResultFileName();
		final String chosenPath = "/home/linkedrh/Desktop/frankensteiner/app";
		assertTrue(PathService.isFileExistent(resultFileName, chosenPath));
	}

	private List<FileData> getExampleContentFromEachFile() {
		List<FileData> contentFromEachFile = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FileData f = new FileData();
			f.setContent(new StringBuilder().append(this.getRandomString(50)));
			f.setName(i + ".sql");
			contentFromEachFile.add(f);
		}
		return contentFromEachFile;

	}

	private String getRandomString(int amountOfElements) {
		byte[] array = new byte[amountOfElements];
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}

}
