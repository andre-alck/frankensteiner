package app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Answers;

class StitchServiceTest {
	private static final int AMOUNT_OF_TXT_FILES = 5;

	@Test
	void givenFolderContainingFiveFiles_whenEachFileIsRead_shouldStoreItsData() {
		StitchService stitchService = mock(StitchService.class, Answers.CALLS_REAL_METHODS);
		when(stitchService.getExtension()).thenReturn(Extensions.TXT);

		List<FileData> singleFile = stitchService.readFiles("/home/linkedrh/Desktop/StitchServiceTest/givenFolderContainingFiveFiles_whenEachFileIsRead_shouldStoreItsData");
		assertEquals(StitchServiceTest.AMOUNT_OF_TXT_FILES, singleFile.size());
	}

}
