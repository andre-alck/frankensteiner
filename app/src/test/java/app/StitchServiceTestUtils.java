package app;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

public class StitchServiceTestUtils {
	public static final String CONTENT_IDENTIFIER = "content";
	public static final String NAME_IDENTIFIER = "name";

	public static List<FileData> generateListOfFilesWithSpecificContent(String[] contents) {
		List<FileData> contentFromEachFile = new ArrayList<>();
		Stream.of(contents).forEach(t -> {
			FileData f = new FileData();
			f.setContent(new StringBuilder(StitchServiceTestUtils.CONTENT_IDENTIFIER + t));
			f.setName(StitchServiceTestUtils.NAME_IDENTIFIER + t);
			contentFromEachFile.add(f);
		});

		return contentFromEachFile;
	}

	public static List<FileData> getExampleContentFromEachFile() {
		List<FileData> contentFromEachFile = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			FileData f = new FileData();
			f.setContent(new StringBuilder().append(StitchServiceTestUtils.getRandomString(2)));
			f.setName(i + ".sql");
			contentFromEachFile.add(f);
		}
		return contentFromEachFile;
	}

	private static String getRandomString(int amountOfElements) {
		byte[] array = new byte[amountOfElements];
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		return generatedString;
	}
}
