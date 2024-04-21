package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StitchServiceSQLImplTest {

	@BeforeEach
	void cleanStitchedFiles() {
		File directory = new File(StitchServiceTestUtils.STITCHED_FILE_PATH);
		for (File f : directory.listFiles(this.filterByName())) {
			f.delete();
		}
	}

	private FilenameFilter filterByName() {
		return new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(new StitchServiceSQLImpl().getExtension().name().toLowerCase());
			}
		};
	}

	@Test
	void givenCallForStitchingMethod_whenFileCreationIsChecked_thenShouldReturnTrue() throws IOException {
		StitchService stitchService = new StitchServiceSQLImpl();

		stitchService.writeFile(StitchServiceTestUtils.getExampleContentFromEachFile(stitchService.getExtension().name()), new AlphabeticalSorting());

		String resultFileName = stitchService.getResultFileName();
		assertTrue(PathService.isFileExistent(resultFileName, StitchServiceTestUtils.STITCHED_FILE_PATH));
	}

	@Test
	void givenCallForStitchingMethod_whenFileCreatedContentIsRead_thenShouldContainExampleData() throws IOException {
		StitchService stitchService = new StitchServiceSQLImpl();

		String[] contents = { "s", "t", "r", "i", "n", "g" };
		List<FileData> filesData = StitchServiceTestUtils.generateListOfFilesWithSpecificContent(contents);

		stitchService.writeFile(filesData, new AlphabeticalSorting());
		List<FileData> stitchedFileData = stitchService.readFiles(StitchServiceTestUtils.STITCHED_FILE_PATH);

		String expectedContent = stitchService.getConcatenatedStringThroughFileData(filesData);
		String stitchedFileContent = stitchService.getConcatenatedStringThroughFileData(stitchedFileData);

		assertEquals(expectedContent, stitchedFileContent);
	}

}
