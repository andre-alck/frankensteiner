package app;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class StringUtilsTest {
	private static final String ABC_STRING_WITH_NO_DOT = "abc";

	@ParameterizedTest
	@ValueSource(strings = { ".abc", "abc" })
	void givenStringWithOrWithoutDot_whenRemovingDotIfNeedIsCalled_thenShouldRemoveThePossibleDot(String content) {
		String resultContent = StringUtils.removeDotIfNeeded(content);
		assertEquals(StringUtilsTest.ABC_STRING_WITH_NO_DOT, resultContent);
	}
}
