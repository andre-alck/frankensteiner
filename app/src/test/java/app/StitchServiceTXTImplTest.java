package app;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;

class StitchServiceTXTImplTest {

private static final String STITCHED_FILE_PATH = "/home/linkedrh/Desktop/frankensteiner/app";
	
	@BeforeEach
	void cleanStitchedFiles() {
		File directory = new File(StitchServiceTXTImplTest.STITCHED_FILE_PATH);
		for (File f : directory.listFiles(this.filterByName())) {
			f.delete();
		}
	}

	private FilenameFilter filterByName() {
		return new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(new StitchServiceTXTImpl().getExtension().name().toLowerCase());
			}
		};
	}

	@Test
	void givenCallForTXTStitchingMethod_whenFileCreationIsChecked_thenShouldReturnTrue() throws IOException {
		StitchService stitchService = mock(StitchServiceTXTImpl.class, Answers.CALLS_REAL_METHODS);
		when(stitchService.getFormattedDate()).thenReturn("2024/04/14 19:30:59");

		stitchService.writeFile(StitchServiceTestUtils.getExampleContentFromEachFile(stitchService.getExtension().name()), new AlphabeticalSorting());

		final String resultFileName = stitchService.getResultFileName();
		final String chosenPath = StitchServiceTXTImplTest.STITCHED_FILE_PATH;
		assertTrue(PathService.isFileExistent(resultFileName, chosenPath));
	}

	@Test
	void givenCallForTXTStitchingMethod_whenFileCreatedContentIsRead_thenShouldContainExampleData() throws IOException {
		StitchService stitchService = mock(StitchServiceTXTImpl.class, Answers.CALLS_REAL_METHODS);
		final String fileName = "givenCallForTXTStitchingMethod_whenFileCreatedContentIsRead_thenShouldContainExampleData.txt";
		when(stitchService.getResultFileName()).thenReturn(fileName);

		String[] contents = { "s", "t", "r", "i", "n", "g" };
		List<FileData> filesData = StitchServiceTestUtils.generateListOfFilesWithSpecificContent(contents);
		stitchService.writeFile(filesData, new AlphabeticalSorting());
		String expectedContent = stitchService.getConcatenatedStringThroughFileData(filesData);

		final String chosenPath = StitchServiceTXTImplTest.STITCHED_FILE_PATH;
		List<FileData> stitchedFileData = stitchService.readFiles(chosenPath);
		String stitchedFileContent = stitchService.getConcatenatedStringThroughFileData(stitchedFileData);

		assertEquals(expectedContent, stitchedFileContent);
	}

}
