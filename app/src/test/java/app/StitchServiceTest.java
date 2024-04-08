package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.InOrder;

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

		InOrder inOrder = inOrder(stitchService);
		inOrder.verify(stitchService, times(1)).readFiles(anyString());
		inOrder.verify(stitchService, times(1)).writeFile(filesData, sortingWay);
	}

}
