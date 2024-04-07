package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

@TestInstance(Lifecycle.PER_CLASS)
class FileDataTest {
	private static final int LESS_THAN = -1;
	private static final int EQUAL_TO = 0;
	private static final int GREATER_THAN = 1;
	private static final String A = "A";
	private static final String B = "B";

	FileData fileDataWithNameThatStartsWithA = null;
	FileData fileDataWithNameThatStartsWithB = null;

	@BeforeAll
	void setup() {
		this.fileDataWithNameThatStartsWithA = new FileData();
		this.fileDataWithNameThatStartsWithA.setName(FileDataTest.A);

		this.fileDataWithNameThatStartsWithB = new FileData();
		this.fileDataWithNameThatStartsWithB.setName(FileDataTest.B);
	}

	@Test
	void givenTwoFileDataObjects_whenComparingAlphabeticallyElementThatStartsWithAnAToAnElementThatStartsWithAB_shouldReturnLessThan() {
		int comparation = this.fileDataWithNameThatStartsWithA.compareTo(this.fileDataWithNameThatStartsWithB);
		assertEquals(FileDataTest.LESS_THAN, comparation);
	}

	@Test
	void givenTwoFileDataObjects_whenComparingAlphabeticallyElementThatStartsWithABToAnElementThatStartsWithAnA_shouldReturnGreaterThan() {
		int comparation = this.fileDataWithNameThatStartsWithB.compareTo(this.fileDataWithNameThatStartsWithA);
		assertEquals(FileDataTest.GREATER_THAN, comparation);
	}

	@Test
	void givenTwoFileDataObjects_whenComparingAlphabeticallyElementsThatStartsWithTheSameLetter_shouldReturnEqualTo() {
		FileData anotherFileDataWithNameThatStartsWithA = new FileData();
		anotherFileDataWithNameThatStartsWithA.setName(FileDataTest.A);

		int comparation = anotherFileDataWithNameThatStartsWithA.compareTo(fileDataWithNameThatStartsWithA);
		assertEquals(FileDataTest.EQUAL_TO, comparation);
	}

}
