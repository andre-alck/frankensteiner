package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InOrder;
import org.mockito.Mockito;

class StitchServiceTest {
	private static final int AMOUNT_OF_TXT_FILES = 5;

	@Test
	void givenFolderContainingFiveFiles_whenEachFileIsRead_thenShouldStoreItInList() {
		StitchService stitchService = mock(StitchService.class, Answers.CALLS_REAL_METHODS);
		when(stitchService.getExtension()).thenReturn(Extensions.TXT);

		List<FileData> filesData = stitchService.readFiles("/home/linkedrh/Desktop/StitchServiceTest/givenFolderContainingFiveFiles_whenEachFileIsRead_shouldStoreItsData");
		assertEquals(StitchServiceTest.AMOUNT_OF_TXT_FILES, filesData.size());
	}

	@Test
	void givenStichingMethod_whenVerifyingItsLogic_shouldReadThenWrite() {
		StitchService stitchService = mock(StitchService.class);
		doCallRealMethod().when(stitchService).stitch(anyString(), any(SortingWay.class));

		SortingWay sortingWay = new AlphabeticalSorting();
		List<FileData> filesData = new ArrayList<>();
		stitchService.stitch("", sortingWay);

		InOrder inOrder = Mockito.inOrder(stitchService);
		inOrder.verify(stitchService, times(1)).readFiles(anyString());
		inOrder.verify(stitchService, times(1)).writeFile(filesData, sortingWay);
	}

	@Test
	void givenCallForReceivingResultFileName_whenUsingMockWithFixedDate_shouldConcatenate() {
		final String now = "2024_04_08 23h15h10";
		final Extensions extension = Extensions.SQL;
		StitchService stitchService = mock(StitchService.class, Answers.CALLS_REAL_METHODS);
		when(stitchService.getExtension()).thenReturn(extension);
		when(stitchService.getFormattedDate()).thenReturn(now);
		doCallRealMethod().when(stitchService).getResultFileName();

		String resultFileName = stitchService.getResultFileName();
		String expectedResultFileName = now + "." + extension.name().toLowerCase();

		assertEquals(expectedResultFileName, resultFileName);
	}

	@Test
	void givenFileData_whenConcatenatingContentFromAllFiles_shouldReturnStringWithContent() {
		String[] contents = { "s", "t", "r", "i", "n", "g" };
		List<FileData> filesData = StitchServiceTestUtils.generateListOfFilesWithSpecificContent(contents);
		for (int i = 0; i < contents.length; i++) {
			assertTrue(filesData.get(i).getName().equals(StitchServiceTestUtils.NAME_IDENTIFIER + contents[i]));
			assertTrue(filesData.get(i).getContent().toString().equals(StitchServiceTestUtils.CONTENT_IDENTIFIER + contents[i]));
		}
	}

}
