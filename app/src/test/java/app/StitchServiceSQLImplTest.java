package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;

class StitchServiceSQLImplTest {

	@Test
	void givenCallForStitchingMethod_whenFileCreationIsChecked_thenShouldReturnTrue() throws IOException {
		StitchService stitchService = mock(StitchServiceSQLImpl.class, Answers.CALLS_REAL_METHODS);
		when(stitchService.getFormattedDate()).thenReturn("2024/04/14 19:30:59");

		stitchService.writeFile(StitchServiceTestUtils.getExampleContentFromEachFile(), new AlphabeticalSorting());

		final String resultFileName = stitchService.getResultFileName();
		final String chosenPath = "/home/linkedrh/Desktop/frankensteiner/app";
		assertTrue(PathService.isFileExistent(resultFileName, chosenPath));
	}

	// TODO this test will pass only if there's just one file in the chosenPath
	// directory below. refactor to generate a file with specific name to search for
	@Test
	void givenCallForStitchingMethod_whenFileCreatedContentIsRead_thenShouldContainExampleData() throws IOException {
		StitchService stitchService = mock(StitchServiceSQLImpl.class, Answers.CALLS_REAL_METHODS);
		final String fileName = "givenCallForStitchingMethod_whenFileCreatedContentIsRead_thenShouldContainExampleData.sql";
		when(stitchService.getResultFileName()).thenReturn(fileName);

		String[] contents = { "s", "t", "r", "i", "n", "g" };
		List<FileData> filesData = StitchServiceTestUtils.generateListOfFilesWithSpecificContent(contents);
		stitchService.writeFile(filesData, new AlphabeticalSorting());
		String expectedContent = stitchService.getConcatenatedStringThroughFileData(filesData);

		final String chosenPath = "/home/linkedrh/Desktop/frankensteiner/app";
		List<FileData> stitchedFileData = stitchService.readFiles(chosenPath);
		String stitchedFileContent = stitchService.getConcatenatedStringThroughFileData(stitchedFileData);

		assertEquals(expectedContent, stitchedFileContent);
	}

}
