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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
			assertTrue(filesData.get(i).getContent().toString()
					.equals(StitchServiceTestUtils.CONTENT_IDENTIFIER + contents[i]));
		}
	}

	@Test
	void givenListOfFileData_whenSortingAlphabetically_shouldSortListByName() {
		StitchService stitchService = mock(StitchService.class, Answers.CALLS_REAL_METHODS);

		List<FileData> filesData = this.generateUnsortedListContainingAlphaticalCharactersAndRandomModificationDate();
		stitchService.sort(filesData, new AlphabeticalSorting());

		List<String> orderedListOfAlphabeticalCharacters = Arrays.asList(this.getAlphabeticalCharacters());
		Collections.sort(orderedListOfAlphabeticalCharacters);
		for (int i = 0; i < filesData.size(); i++) {
			assertEquals(orderedListOfAlphabeticalCharacters.get(i), filesData.get(i).getName());
		}
	}

	private List<FileData> generateUnsortedListContainingAlphaticalCharactersAndRandomModificationDate() {
		String[] contents = this.getAlphabeticalCharacters();
		List<LocalDateTime> dates = this.getListOfLocalDateTime();

		List<FileData> filesData = new ArrayList<>();
		for (int i = 0; i < contents.length; i++) {
			FileData fileData = new FileData();
			fileData.setName(contents[i]);
			fileData.setModificationDate(dates.get(i));
			filesData.add(fileData);
		}
		return filesData;
	}

	private String[] getAlphabeticalCharacters() {
		return new String[] { "z", "a", "b", "y", "c", "f" };
	}

	private List<LocalDateTime> getListOfLocalDateTime() {
		List<LocalDateTime> listOfLocalDateTime = new ArrayList<>();
		for (int i = 0; i < 6; i++) {
			listOfLocalDateTime.add(LocalDateTime.now().plusYears(new Random().nextInt(10)));
		}
		return listOfLocalDateTime;
	}

	@Test
	void givenListOfFileData_whenSortingByModificationDate_shouldSortListByModificationDate() {
		StitchService stitchService = mock(StitchService.class, Answers.CALLS_REAL_METHODS);

		List<FileData> filesData = this.generateUnsortedListContainingAlphaticalCharactersAndRandomModificationDate();
		stitchService.sort(filesData, new ModificationDateSorting());

		List<LocalDateTime> orderedLocalDateTimeFromFilesData = filesData.stream().map(FileData::getModificationDate).collect(Collectors.toList());
		List<LocalDateTime> copy = new ArrayList<>(orderedLocalDateTimeFromFilesData);
		Collections.sort(copy);

		for (int i = 0; i < 6; i++) {
			assertEquals(orderedLocalDateTimeFromFilesData.get(i), copy.get(i));
		}
	}

}
